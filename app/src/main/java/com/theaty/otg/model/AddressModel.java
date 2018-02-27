package com.theaty.otg.model;

//收货地址模型
public class AddressModel extends BaseModel {
    public int address_id;// 地址ID',
    public int member_id;// '会员ID',
    public String true_name;// '会员姓名',
    public int province_id;// '省份ID',
    public int city_id;// '市级ID',
    public int area_id;// '区县ID',
    public String province_name;// '省份名字',
    public String city_name;// '市级名字',
    public String area_name;// '区县名字',
    public String area_info;// '地区内容'
    public String address;// '地址'
    public String tel_phone;// '座机电话',
    public String mob_phone;// '手机电话',
    public int is_default;// '1默认收货地址',
    public int dlyp_id;// '自提点ID',
    public String post_code; // 邮政编码
    public String reciver_name;
    public AddressModel reciver_info; // 订单详情中的地址

    // 默认值
    public AddressModel() {
        address_id = 0;// 地址ID',
        member_id = 0;// '会员ID',
        true_name = "";// '会员姓名',
        province_id = 0;// '省份ID',
        city_id = 0;// '市级ID',
        area_id = 0;// '区县ID',
        area_info = "";// '地区内容'
        address = "";// '地址'
        tel_phone = "";// '座机电话',
        mob_phone = "";// '手机电话',
        is_default = 0;// '1默认收货地址',
        dlyp_id = 0;// '自提点ID'
        post_code = "";
        province_name = "";// '省份名字',
        city_name = "";// '市级名字',
        area_name = "";// '区县名字',
        reciver_name = "";
    }
}
