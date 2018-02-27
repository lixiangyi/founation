package com.theaty.otg.system;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.theaty.otg.model.MemberModel;

import java.lang.reflect.Type;
import java.util.ArrayList;

import foundation.toast.ToastUtil;
import foundation.util.StringUtil;

/**
 * 数据存取
 *
 * @author
 */
public class DatasStore {
	private static AppContext myApp = AppContext.getInstance();
	public static SharedPreferences infoSP = myApp.getSharedPreferences("songqi_deliver_info", Context.MODE_PRIVATE);
	private static final String KEY_FIRST_LAUNCH = "fistLaunch";
	private static final String KEY_USER_PHONE = "key_user_phone";
	private static final String KEY_USER_PASSWORD = "key_user_password";
	//用户城市
	private static final String KEY_USER_CITY = "key_user_city";

	public static void setFirstLaunch(Boolean mm) {
		infoSP.edit().putBoolean(KEY_FIRST_LAUNCH, mm)
				.commit();
	}

	public static Boolean isFirstLaunch() {
		return infoSP.getBoolean(KEY_FIRST_LAUNCH, true);
	}

	private static String KEY_SAVE_PASSWORD = "isSavePassword";

	/**
	 * 读取登陆页面"记住密码"复选框状态
	 *
	 * @return
	 */
	public static boolean isSavePassword() {
		return infoSP.getBoolean(KEY_SAVE_PASSWORD, false);
	}

	/**
	 * 保存登陆页面"记住密码"复选框状态
	 *
	 * @return
	 */
	public static void setSavePassword(boolean isSave) {
		infoSP.edit().putBoolean(KEY_SAVE_PASSWORD, isSave).commit();
	}

	/**
	 * 保存用户电话登录使用的电话号码
	 *
	 * @param phone
	 */
	public static void saveUserPhone(String phone) {
		infoSP.edit().putString(KEY_USER_PHONE, phone).commit();
	}

	/**
	 * 读取用户电话号码
	 */
	public static String getUserPhone() {
		return infoSP.getString(KEY_USER_PHONE, "");
	}


	/**
	 * 保存用户城市
	 *
	 * @param city
	 */
	public static void saveUserCity(String city) {
		infoSP.edit().putString(KEY_USER_CITY, city).commit();
	}

	/**
	 * 读取用户城市
	 */
	public static String getUserCity() {
		return infoSP.getString(KEY_USER_CITY, "");
	}

	private static final String CUR_MEMBER_KEY = "curMember";

	// 存档会员信息
	public static void setCurMember(MemberModel mm) {
		if (mm == null) {
			ToastUtil.showToast("模型消息：setCurMember 模型为 null");
			return;
		}
		String verString = mm.isLegal();
		if (verString.equals("access")) {
			putObj2Sp(infoSP, CUR_MEMBER_KEY, mm);
		} else {
			ToastUtil.showToast("模型消息：setCurMember" + verString);
		}
	}

	// 获取会员信息
	public static MemberModel getCurMember() {
		MemberModel mmMemberModel = (MemberModel) getObjectFromSp(
				infoSP, CUR_MEMBER_KEY, MemberModel.class);

		if (mmMemberModel == null) {
			return new MemberModel();
		}
		String verString = mmMemberModel.isLegal();
		if (!verString.equals("access")) {
			ToastUtil.showToast("模型消息：getCurMember" + verString);
			return null;
		}

		return mmMemberModel;
	}

	// 清除当前用户
	public static void removeCurMember() {
		infoSP.edit().remove(CUR_MEMBER_KEY).commit();// 移除
	}

	// 判断是否登录
	public static boolean isLogin() {
		if (getCurMember() != null && !StringUtil.isEmpty(getCurMember().key)) {
			return true;
		} else {
			return false;
		}
	}

	static final String SELECT_AREA_FLAG = "selectArea";

	static final String CUR_STATE_FLAG = "curState";



	//
	// gson一个对象并存储 如果为null 就删除原有对象
	public static void putObj2Sp(SharedPreferences sp, String key, Object o) {
		if (o != null) {
			Gson gson = new Gson();
			try {
				String ssString = gson.toJson(o, o.getClass());
				sp.edit().putString(key, ssString).commit();
			} catch (Exception e) {
				Log.e("putObj2Sp json转换出错: ", e.getMessage());
			}
		} else {
			sp.edit().remove(key).commit();
		}
	}

	// 获取一个对象 getObjectFromSp(String key,*.class) 失败返回null
	public static Object getObjectFromSp(SharedPreferences sp, String key, Type type) {
		Gson gson = new Gson();
		String ssString = sp.getString(key, "");
		return gson.fromJson(ssString, type);
	}

	private static final String VERSION_NUM = "version_num";

	public static String getVersionNum() {
		return infoSP.getString(VERSION_NUM, "1");
	}

	public static void saveVersionNum(String versionNum) {
		infoSP.edit().putString(VERSION_NUM, versionNum).apply();
	}

	private static final String KEY_GETUI_CID = "getui_cid";

	public static void setCid(String cid) {
		infoSP.edit().putString(KEY_GETUI_CID, cid).apply();
	}

	public static String getCid() {
		return infoSP.getString(KEY_GETUI_CID, null);
	}

	// ********** 搜索宠物历史 **********
	private static final String SP_SEARCH_PETS_HISTORY = "sp_search_pets_history";

	// 保存单个
	public static void savePetsSearchHistory(String word) {
		ArrayList<String> list = getPetsSearchHistory();
		list.add(word);

		infoSP.edit().putString(SP_SEARCH_PETS_HISTORY, new Gson().toJson(list)).apply();
	}

	// 获取全部
	private static ArrayList<String> getPetsSearchHistory() {
		ArrayList<String> list = new ArrayList<>();

		String json = infoSP.getString(SP_SEARCH_PETS_HISTORY, null);
		if (!TextUtils.isEmpty(json)) {
			list = new Gson().fromJson(json, new TypeToken<ArrayList<String>>() {
			}.getType());
		}

		return list;
	}

	// 移除单个
	public static void removePestSearcHistory(String word) {
		ArrayList<String> list = getPetsSearchHistory();
		list.remove(word);

		infoSP.edit().putString(SP_SEARCH_PETS_HISTORY, new Gson().toJson(list)).apply();
	}

	// ********** 搜索商品历史 **********
	private static final String SP_SEARCH_GOODS_HISTORY = "sp_search_goods_history";

	// 保存单个
	public static void saveGoodsSearchHistory(String word) {
		ArrayList<String> list = getGoodsSearchHistory();
		list.add(word);

		infoSP.edit().putString(SP_SEARCH_GOODS_HISTORY, new Gson().toJson(list)).apply();
	}

	// 获取全部
	private static ArrayList<String> getGoodsSearchHistory() {
		ArrayList<String> list = new ArrayList<>();

		String json = infoSP.getString(SP_SEARCH_GOODS_HISTORY, null);
		if (!TextUtils.isEmpty(json)) {
			list = new Gson().fromJson(json, new TypeToken<ArrayList<String>>() {
			}.getType());
		}

		return list;
	}

	// 移除单个
	public static void removeGoodsSearcHistory(String word) {
		ArrayList<String> list = getGoodsSearchHistory();
		list.remove(word);

		infoSP.edit().putString(SP_SEARCH_GOODS_HISTORY, new Gson().toJson(list)).apply();
	}

	// ********** 搜索店铺历史 **********
	private static final String SP_SEARCH_STORE_HISTORY = "sp_search_store_history";

	// 保存单个
	public static void saveStoreSearchHistory(String word) {
		ArrayList<String> list = getStoreSearchHistory();
		list.add(word);

		infoSP.edit().putString(SP_SEARCH_STORE_HISTORY, new Gson().toJson(list)).apply();
	}

	// 获取全部
	public static ArrayList<String> getStoreSearchHistory() {
		ArrayList<String> list = new ArrayList<>();

		String json = infoSP.getString(SP_SEARCH_STORE_HISTORY, null);
		if (!TextUtils.isEmpty(json)) {
			list = new Gson().fromJson(json, new TypeToken<ArrayList<String>>() {
			}.getType());
		}

		return list;
	}

	// 移除单个
	public static void removeStoreSearcHistory(String word) {
		ArrayList<String> list = getStoreSearchHistory();
		list.remove(word);

		infoSP.edit().putString(SP_SEARCH_STORE_HISTORY, new Gson().toJson(list)).apply();
	}

	// ********** 保存是否自动绑定 **********
	private static final String SP_CONFIG_AUTO_BIND = "sp_config_auto_bind";

	public static void saveConfigAutoBind(int i) {
		infoSP.edit().putInt(SP_CONFIG_AUTO_BIND, i).apply();
	}

	public static int getConfigAutoBind() {
		return infoSP.getInt(SP_CONFIG_AUTO_BIND, 0);
	}

	private static final String SP_LOCAL_COLLECTION = "sp_local_collection";

	// 保存本地收藏的活体商品id
	public static void saveLocalCollection(int goods_id) {
		String ids = getLocalCollection();
		if (!TextUtils.isEmpty(ids)) {
			ids += "," + goods_id;
		} else {
			ids = String.valueOf(goods_id);
		}
		setLocalCollection(ids);
	}

	// 删除本地收藏的活体商品id
	public static void removeLocalCollection(int goods_id) {
		String str = getLocalCollection();
		String[] local;
		if (str.contains(",")) {
			local = str.split(",");
		} else {
			local = new String[]{str};
		}

		String end = "";
		String id = String.valueOf(goods_id);
		for (int i = 0; i < local.length; i++) {
			if (!local[i].equals(id)) {
				end += local[i] + ",";
			}
		}

		setLocalCollection(end);
	}

	// 批量本地收藏的活体商品id
	public static void removeLocalCollection(String ids) {
		String[] split;
		if (ids.contains(",")) {
			split = ids.split(",");
		} else {
			split = new String[]{ids};
		}

		String str = getLocalCollection();
		String[] local;
		if (str.contains(",")) {
			local = str.split(",");
		} else {
			local = new String[]{str};
		}

		for (int i = 0; i < split.length; i++) {
			for (int j = 0; j < local.length; j++) {
				if (split[i].equals(local[j])) {
					local[j] = "";
					break;
				}
			}
		}

		String end = "";
		for (int i = 0; i < local.length; i++) {
			if (!TextUtils.isEmpty(local[i])) {
				end += local[i] + ",";
			}
		}

		setLocalCollection(end);
	}

	// 判断本地收藏的活体商品id
	public static boolean isLocalCollection(int goods_id) {
		String ids = getLocalCollection();
		String[] split;
		if (ids.contains(",")) {
			split = ids.split(",");
		} else {
			split = new String[]{ids};
		}

		String id = String.valueOf(goods_id);
		for (int i = 0; i < split.length; i++) {
			if (id.equals(split[i])) {
				return true;
			}
		}

		return false;
	}

	// 保存本地收藏的活体商品id
	public static void setLocalCollection(String ids) {
		if (ids.endsWith(",")) {
			ids = ids.substring(0, ids.length() - 1);
		}
		infoSP.edit().putString(SP_LOCAL_COLLECTION, ids).commit();
	}

	// 获取本地收藏的活体商品id
	public static String getLocalCollection() {
		return infoSP.getString(SP_LOCAL_COLLECTION, "");
	}
}
