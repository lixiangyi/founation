package com.theaty.otg.system;


import android.app.Activity;


/**
 * Created by Administrator on 2016/4/29.
 */
public class UpdateManager {

    private static Activity mActivity;

    public static void checkUpdate(Activity activity){
        mActivity = activity;
//        new MemberModel().mb_version(new BaseModel.BaseModelIB() {
//            @Override
//            public void StartOp() {}
//
//            @Override
//            public void successful(Object o) {
//                if (null != o && o instanceof SystemModel) {
//                    final SystemModel bean = (SystemModel) o;
//                    if (AppUtils.isApplicationUpdatable(mActivity, mActivity.getPackageName(), (int) bean.version_num)) {
//                        new AlertDialog.Builder(mActivity).setMessage("检测到新版本，是否更新？").setPositiveButton("更新", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Intent intent = new Intent(Intent.ACTION_VIEW);
//                                intent.setData(Uri.parse(bean.update_url));
//                                mActivity.startActivity(intent);
//                                dialog.dismiss();
//                            }
//                        }).setNegativeButton("取消", null).show();
//                    }
//                }
//            }
//
//            @Override
//            public void failed(ResultsModel resultsModel) {}
//        });
    }
}
