package com.yifu.ladianbao.net

/**
 * Copyright (c) 山东六牛网络科技有限公司 https://liuniukeji.com
 *
 * @Description
 * @Author (沐枫/hanlin_bj@163.com)
 * @Copyright Copyright (c) 山东六牛网络科技有限公司 保留所有版权(https://www.liuniukeji.com)
 * @Date 2018
 * @CreateBy android_studio
 * @Remarks
 */
data class SimpleResponse (var status: Int,var info: String){

    fun toBaseResponse() : BaseResponse<Void> {
        return BaseResponse(status, info)
    }

}