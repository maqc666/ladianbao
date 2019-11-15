package com.yifu.ladianbao.net

/**
 * Copyright (c) 山东六牛网络科技有限公司 https://liuniukeji.com
 *
 * @Description
 * @Author         与天同行的观测者
 * @Copyright      Copyright (c) 山东六牛网络科技有限公司 保留所有版权(https://www.liuniukeji.com)
 * @Date           $date$ $time$
 */
object UrlUtils {
    /**
     * 测试地址
     */
    const val domain = "http://yali.new.pro3.liuniukeji.net/"//测试地址
    /**
     * 正式地址
     */
    // private const val domain = ""//正式地址

    //地址名加 api/
    private const val api_http = domain + "Api/"//正式地址

    /**
     * 接口名
     */
    /***
     * 我的
     */
    //登录
    val login: String = api_http + "PublicApi/login"
    //首页
    val storeinfo: String = api_http + "Index/storeinfo"
    //退出
    val loginout: String = api_http + "Index/Loginout"
    //我的-首页
    val index: String = api_http + "Store/index"
    //我的-店铺资料修改页面
    val storeInfo: String = api_http + "Store/storeInfo"
    //会员详情
    val memberinfo: String = api_http + "Member/memberinfo"
    //1.首页会员充值/消费结账 所有用户 余额充值或消费记录列表
    val rechargelog: String = api_http + "Member/rechargelog"
    //2.首页 会员充值 所有用户 次卡充值记录列表
    val cardAddcord: String = api_http + "Member/cardAddcord"
    //3.首页 会员结账 所有用户 次卡消费记录列表
    val cardcord: String = api_http + "Member/cardcord"
    //4.会员列表
    val memberlist: String = api_http + "Member/memberlist"
    //5.会员详情-余额消费
    val consume: String = api_http + "Member/consume"
    //6.会员详情 - 次卡充值 次卡列表
    val vipcard: String = api_http + "Member/vipcard"
    //7.会员详情- 次卡消费- 次卡列表*
    val membercard: String = api_http + "Member/membercard"
    //8.会员详情-次卡充值-次卡商品列表-次卡充值详情 获取数据
    val cardinfo: String = api_http + "Member/cardinfo"
    //9.会员详情-次卡充值-次卡商品列表-次卡充值详情-充值
    val givecard: String = api_http + "Member/givecard"
    //10.会员详情-次卡消费-会员次卡消费详情-获取数据
    val membercardinfo: String = api_http + "Member/membercardinfo"
    //次卡消费（结账）*
    val carddec: String = api_http + "Member/carddec"
    //会员列表-详情-余额明细
    val recharge: String = api_http + "Member/recharge"
    //存储卡充值
    val rechangeAdd: String = api_http + "Member/rechangeAdd"
    // 2.2会员详情-注销会员√
    val delmember: String = api_http + "Member/delmember"
    //2.3会员次卡使用明细*
    val getCardLog: String = api_http + "Member/getCardLog"
    //3.1会员次卡明细（单卡详情）
    val getOneLog: String = api_http + "Member/getOneLog"
    //添加會員
    val memberAdd: String = api_http + "Member/memberAdd"

    /**关于我们*/
    //关于我们
    val aboutUs: String = api_http + "PublicApi/doc/id/4"
    //官方邮箱及电话
    val info: String = api_http + "PublicApi/info"
    //意见反馈
    val advice: String = api_http + "PublicApi/advice"
    /**修改密码*/
    //发送短信接口
    val smsCode: String = api_http + "PublicApi/smsCode"
    //修改密码
    val changePwd: String = api_http + "PublicApi/changePwd"
    /**优惠券*/
    //1.选择分类列表（添加优惠券）
    val category: String = api_http + "Coupon/category"
    //2.选择商品列表（添加优惠券）
    val goodsList: String = api_http + "Coupon/goodsList"
    //3.优惠券添加
    val couponAdd: String = api_http + "Coupon/couponAdd"
    //优惠券列表
    val couponlist: String = api_http + "Coupon/couponlist"
    //删除
    val del: String = api_http + "Coupon/del"
    //发行，禁用优惠券
    val setStatus: String = api_http + "Coupon/setStatus"

    /**投票*/
    //1.新增
    val toupiaoAddToupiao: String = api_http + "Toupiao/addToupiao"
    //2.活动列表
    val toupiaoIndex: String = api_http + "Toupiao/index"
    //3.修改活动发布状态√
    val toupiaoSetStatus: String = api_http + "Toupiao/setStatus"
    //4.删除活动
    val toupiaoDel: String = api_http + "Toupiao/del"

    /**助力*/
    //1.活动添加
    val addZhuli: String = api_http + "Zhuli/addZhuli"
    //2.活动列表
    val zhuliIndex: String = api_http + "Zhuli/index"
    //3.删除活动
    val zhuliDel: String = api_http + "Zhuli/del"
    //4.修改活动发布状态
    val zhuliSetStatus: String = api_http + "Zhuli/setStatus"

    /**砍价*/

    //1.活动添加
    val addKanjia: String = api_http + "Kanjia/addKanjia"
    //2.活动列表
    val kanjiaIndex: String = api_http + "Kanjia/index"
    //3.修改活动发布状态
    val kanjiaSetStatus: String = api_http + "Kanjia/setStatus"
    //修改活动名称
    val kanjiaModifyName: String = api_http + "Kanjia/modifyName"
    //删除活动
    val kanjiaDel: String = api_http + "Kanjia/del"

    /**商家入驻*/
    val usercheck: String = api_http + "OpenStore/usercheck"
}