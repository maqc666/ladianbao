package com.yifu.ladianbao.ui.finance.business

class BusinessListBean {
   var page_count=""

    var data = arrayListOf<DataBean>()

     class DataBean{
        var id: String?=null
        var agent_id: String?=null
        var business_id: String?=null
        var alias_id: String?=null
        var from_type: String?=null
        var account_type: String?=null
        var sign: String?=null
        var money: String?=null
        var create_time: String?=null
        var remark: String?=null
    }

}