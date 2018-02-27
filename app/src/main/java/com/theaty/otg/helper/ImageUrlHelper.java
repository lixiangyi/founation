package com.theaty.otg.helper;

import static com.theaty.otg.model.BaseModel.API_IMAGE_HOST_PRE;

/**
 * Created by  on 2017/3/4.
 * <p>
 * 图片地址帮助
 */

public class ImageUrlHelper {
    //http://localhost/ttshop/data/uploadshop/store/goods/1/1_04418207207476705.jpg
    public static String getGoodImageUrl(int store_id, String imageUrl) {
        return String.format(API_IMAGE_HOST_PRE + "%s/%s", store_id, imageUrl);
    }
}
