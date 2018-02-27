package com.theaty.otg.enums;

import foundation.enums.EnumsBaseType;

/**
 * Created by wujian on 2016/12/25.
 * <p>
 * 发布类型类型
 */

public enum SearchType {
    /**
     * 宠市
     */
    PETCITY(0),
    /**
     * 宠舍
     */
    PETHOUSE(1);



    private int code;

    SearchType(int code) {
        this.code = code;
        EnumsBaseType._basecode = this.code + 1;
    }

    SearchType() {
        this.code = EnumsBaseType._basecode;
        EnumsBaseType._basecode++;
    }


    public int getCode() {
        return code;
    }
}
