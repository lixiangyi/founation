package com.theaty.otg.model;


import android.text.TextUtils;

/**
 * @author Theaty
 * @desc 会员模型
 */
public class MemberModel extends BaseModel {

    public int message_type;
    public int trace_id;
    /**
     * 背景
     */
    public String member_back;


    /**
     * freeze_points',
     */
    public int freeze_points;
    /**
     * 个性签名',
     */
    public String member_sign;
    public String conversationId;
    /**
     * 绑定店铺id',
     */
    public int bind_storeid;
    /**
     * 点赞数',
     */
    public int like_num;
    /**
     * 会员id',
     */
    public int member_id;
    /**
     * 校验key
     */
    public String key;
    /**
     * 会员名称',
     */
    public String member_name;
    /**
     * 会员昵称
     */
    public String member_nick;
    /**
     * 真实姓名',
     */
    public String member_truename;
    /**
     * 会员头像',
     */
    public String member_avatar;
    /**
     * 会员性别 (男1，女2)
     */
    public int member_sex;
    /**
     * 生日',
     */
    public String member_birthday;
    /**
     * 会员密码',
     */
    public String member_passwd;
    /**
     * 支付密码',
     */
    public String member_paypwd;
    /**
     * 会员邮箱',
     */
    public String member_email;

    /**
     * 可用充值卡余额
     */
    public double available_rc_balance;
    /**
     * 冻结充值卡余额
     */
    public double freeze_rc_balance;
    /**
     * 0未绑定1已绑定',
     */
    public boolean member_email_bind;
    /**
     * 手机号',
     */
    public String member_mobile;
    /**
     * 0未绑定1已绑定',
     */
    public boolean member_mobile_bind;
    /**
     * qq',
     */
    public String member_qq;
    /**
     * 阿里旺旺',
     */
    public String member_ww;
    /**
     * 登录次数',
     */
    public int member_login_num;
    /**
     * 会员注册时间',
     */
    public String member_time;
    /**
     * 当前登录时间',
     */
    public String member_login_time;
    /**
     * 上次登录时间',
     */
    public String member_old_login_time;
    /**
     * 当前登录ip',
     */
    public String member_login_ip;
    /**
     * 上次登录ip',
     */
    public String member_old_login_ip;
    /**
     * qq互联id',
     */
    public String member_qqopenid;
    /**
     * qq账号相关信息',
     */
    public String member_qqinfo;
    /**
     * 新浪微博登录id',
     */
    public String member_sinaopenid;
    /**
     * 新浪账号相关信息序列化值',
     */
    public String member_sinainfo;
    /**
     * 微信登录openid
     */
    public String member_wx_openid;
    /**
     * 会员积分',
     */
    public int member_points;
    /**
     * 预存款可用金额',
     */
    public double available_predeposit;
    /**
     * 运费金额',
     */
    public double order_goods_num;
    /**
     * 订单总金额',
     */
    public String order_shipping_sum;
    /**
     * 预存款冻结金额',
     */
    public double freeze_predeposit;

    /**
     * 是否允许举报(1可以/2不可以)',
     */
    public int inform_allow;
    /**
     * 会员是否有购买权限 1为开启 0为关闭',
     */
    public boolean is_buy;
    /**
     * 会员是否有咨询和发送站内信的权限 1为开启 0为关闭',
     */
    public boolean is_allowtalk;
    /**
     * 会员的开启状态 1为开启 0为关闭',
     */
    public boolean member_state;
    /**
     * 会员信用',
     */
    public int member_credit;
    /**
     * sns空间访问次数',
     */
    public int member_snsvisitnum;
    /**
     * 地区id',
     */
    public int member_areaid;
    /**
     * 城市id',
     */
    public int member_cityid;
    /**
     * 省份id',
     */
    public int member_provinceid;
    /**
     * 地区内容',
     */
    public String member_areainfo;
    /**
     * 隐私设定',
     */
    public String member_privacy;
    /**
     * 会员常用操作',
     */
    public String member_quicklink;
    /**
     * 是否宅配，0表示否（默认），1表示是
     */
    public int member_isVip;
    /**
     * 会员经验值',
     */
    public int member_exppoints;
    /**
     * 服务销售员id
     */
    public String seller_num;
    /**
     * 环信用户名
     */
    public String HX_userName;
    /**
     * 环信用户密码
     */
    public String HX_userPwd;
    /**
     * 是否代理商(0否，1是)
     */
    public String is_agent;
    /**
     * 是否中宠会员，0不是，1是，2审核中
     */
    public int is_pet_vip;
    /**
     * 是否活体供应商
     */
    public int is_living;
    /**
     * 是否关注
     */
    public int is_focus;
    /**
     * 粉丝数
     */
    public int my_fans_num;
    /**
     * 关注数
     */
    public int my_focus_num;
    /**
     * 消息数目
     */
    public int message_count;
    /**
     * 关注id
     */
    public int focus_id;
    /**
     * 关注人id
     */
    public int focus_from_id;
    /**
     * 被关注人id
     */
    public int focus_to_id;
    /**
     * 关注时间
     */
    public String focus_time;
    /**
     * 是否已关注(0:未关注,1:已关注)
     */
    public int is_mutual_focus;
    /**
     * 身份认证状态(0:未认证;1:认证通过;2:审核中;3:审核失败)
     */
    public int approve_state;
    /**
     * 审核失败原因
     */
    public String approve_why;
//    /**
//     * 关注数
//     *
//     */
//    public int my_focus_num;
    /**
     * 直播是否可以添加宠物(0:不可以;1:可添加实物商品;2:可添加活体商品)
     */
    public int live_goods;
    /**医生**/
    /**
     * 简介
     */
    public String doctor_content;
    /**
     *医院
     */
    public String doctor_hospital;
    /**
     * 年龄
     */
    public String doctor_age;
    /**
     * 职称
     */
    public String doctor_title;


////初始化默认值
// public MemberModel() {
//member_id  = 0;//会员id',
//member_name  = "";//会员名称',
//member_truename  = "";//真实姓名',
//member_avatar  = "";//会员头像',
//member_sex  = 0;//会员性别',
//member_birthday  = "";//生日',
//member_passwd  = "";//会员密码',
//member_paypwd  = "";//支付密码',
//member_email  = "";//会员邮箱',
//member_email_bind  = 0;//0未绑定1已绑定',
//member_mobile  = "";//手机号',
//member_mobile_bind  = 0;//0未绑定1已绑定',
//member_qq  = "";//qq',
//member_ww  = "";//阿里旺旺',
//member_login_num  = 0;//登录次数',
//member_time  = "";//会员注册时间',
//member_login_time  = "";//当前登录时间',
//member_old_login_time  = "";//上次登录时间',
//member_login_ip  = "";//当前登录ip',
//member_old_login_ip  = "";//上次登录ip',
//member_qqopenid  = "";//qq互联id',
//member_qqinfo  = "";//qq账号相关信息',
//member_sinaopenid  = "";//新浪微博登录id',
//member_sinainfo  = "";//新浪账号相关信息序列化值',
//member_points  = 0;//会员积分',
//available_predeposit  = 0;//预存款可用金额',
//freeze_predeposit  = 0;//预存款冻结金额',
//inform_allow  = 0;//是否允许举报(1可以/2不可以)',
//is_buy  = 0;//会员是否有购买权限 1为开启 0为关闭',
//is_allowtalk  = 0;//会员是否有咨询和发送站内信的权限 1为开启 0为关闭',
//member_state  = 0;//会员的开启状态 1为开启 0为关闭',
//member_credit  = 0;//会员信用',
//member_snsvisitnum  = 0;//sns空间访问次数',
//member_areaid  = 0;//地区id',
//member_cityid  = 0;//城市id',
//member_provinceid  = 0;//省份id',
//member_areainfo  = "";//地区内容',
//member_privacy  = "";//隐私设定',
//member_quicklink  = "";//会员常用操作',
//}

    /**
     * 检验自身是否一个合法的类型
     *
     * @return
     */
    public String isLegal() {

//		if (member_id <= 0) {
//			return " member_id非法";
//		}
        if (key.length() < 1) {
            return " key非法";
        }
        if (TextUtils.isEmpty(member_name)) {
            return " member_name非法";
        }
        return "access";
    }



}

