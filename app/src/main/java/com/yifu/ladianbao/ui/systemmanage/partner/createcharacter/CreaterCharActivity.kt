package com.yifu.ladianbao.ui.systemmanage.partner.createcharacter

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.guoqi.actionsheet.ActionSheet
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.yifu.ladianbao.R
import com.yifu.ladianbao.base.BaseKActivity
import com.yifu.ladianbao.util.ImageLoader
import kotlinx.android.synthetic.main.activity_creater_char.*
import kotlinx.android.synthetic.main.activity_creater_char.tv_bank
import kotlinx.android.synthetic.main.layout_titlebar_centre.*
import java.util.ArrayList

class CreaterCharActivity : BaseKActivity(), ActionSheet.OnActionSheetSelected {


var state=1
    private var pvBankOptions: OptionsPickerView<String>? = null
    private val cardItem = ArrayList<String>()
    private var selectList: List<LocalMedia> = ArrayList()
    private var pvAreaOptions: OptionsPickerView<String>? = null
    private val AreaItem = ArrayList<String>()


    override fun layoutId(): Int {
        return R.layout.activity_creater_char
    }

    override fun initView() {
        var state=1
        tv_title.text = "增加合伙人"
        image_back.setOnClickListener {
            finish()
        }

        ll_bank.setOnClickListener {
            pvBankOptions!!.show()
        }

        ll_area.setOnClickListener {
            pvAreaOptions!!.show()
        }


        zhengmian!!.setOnClickListener { view -> ActionSheet.showSheet(this, this, null)
        }
        beimian!!.setOnClickListener {
            view -> ActionSheet.showSheet(this, this, null)
        this.state=2}

        initCustomOptionPicker()
        getCardItem()

        initAreaOptionPicker()
        getAreaItem()

    }

    override fun initData() {
    }

    //银行弹窗
    private fun initCustomOptionPicker() {
        var selectindex = 0
        if (tv_bank.text.isNotEmpty()) {
            for (i in 0 until cardItem.size) {
                if (cardItem[i] == tv_bank.text.toString()) {
                    selectindex = i
                    break
                }
            }
        }
        pvBankOptions = OptionsPickerBuilder(this, OnOptionsSelectListener { options1, option2, options3, v ->
            val tx = cardItem[options1]
            tv_bank!!.text = tx
//                mPresenter.onUserInfo("", "", "", "", "", "",
//                        "", "", "", tx, "", "")
        })
                .setLayoutRes(R.layout.pickerview_custom_option) { v ->
                    val tvSubmit = v.findViewById<View>(R.id.tv_finish) as TextView
                    tvSubmit.setOnClickListener { v1 ->
                        pvBankOptions!!.returnData()
                        pvBankOptions!!.dismiss()
                    }
                    val tvConfirm = v.findViewById<View>(R.id.tv_confirm) as TextView
                    tvConfirm.setOnClickListener { v1 ->

                        pvBankOptions!!.dismiss()
                    }
                }
                .isDialog(false)
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setOutSideCancelable(false)
                .build<String>()

        pvBankOptions!!.setPicker(cardItem)//添加数据
        pvBankOptions!!.setSelectOptions(selectindex)
    }

    //银行数据
    private fun getCardItem() {
        cardItem.add("平安银行")
        cardItem.add("浙商银行")
        cardItem.add("中国银行")
        cardItem.add("中国建设银行")
        cardItem.add("上海浦东发展银行")
        cardItem.add("招商银行")
        cardItem.add("中国光大银行")
        cardItem.add("华夏银行")
        cardItem.add("兴业银行")
        cardItem.add("中国工商银行")
        cardItem.add("中国农业银行")
        cardItem.add("临商银行")
        cardItem.add("山东农信")
    }

    //地区弹窗
    private fun initAreaOptionPicker() {
        var selectindex = 0
        if (tv_area.text.isNotEmpty()) {
            for (i in 0 until AreaItem.size) {
                if (AreaItem[i] == tv_area.text.toString()) {
                    selectindex = i
                    break
                }
            }
        }
        pvAreaOptions = OptionsPickerBuilder(this, OnOptionsSelectListener { options1, option2, options3, v ->
            val tx = AreaItem[options1]
            tv_area!!.text = tx
//                mPresenter.onUserInfo("", "", "", "", "", "",
//                        "", "", "", tx, "", "")
        })
                .setLayoutRes(R.layout.pickerview_custom_option) { v ->
                    val tvSubmit = v.findViewById<View>(R.id.tv_finish) as TextView
                    tvSubmit.setOnClickListener { v1 ->
                        pvAreaOptions!!.returnData()
                        pvAreaOptions!!.dismiss()
                    }
                    val tvConfirm = v.findViewById<View>(R.id.tv_confirm) as TextView
                    tvConfirm.setOnClickListener { v1 ->

                        pvAreaOptions!!.dismiss()
                    }
                }
                .isDialog(false)
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setOutSideCancelable(false)
                .build<String>()

        pvAreaOptions!!.setPicker(AreaItem)//添加数据
        pvAreaOptions!!.setSelectOptions(selectindex)
    }

    //地区数据
    private fun getAreaItem() {
        AreaItem.add("兰山区")
        AreaItem.add("罗庄区")
        AreaItem.add("河东区")
        AreaItem.add("沂南县")
        AreaItem.add("郯城县")
        AreaItem.add("沂水县")
        AreaItem.add("苍山县")
        AreaItem.add("费县")
        AreaItem.add("平邑县")
        AreaItem.add("蒙阴县")
        AreaItem.add("临沭县")
    }



    override fun onClick(whichButton: Int) {
        when (whichButton) {
            ActionSheet.CHOOSE_PICTURE ->
                //相册
                choosePic()
            ActionSheet.TAKE_PICTURE ->
                //拍照
                takePic()
            ActionSheet.CANCEL -> {
            }
        }//取消
    }

    private fun choosePic() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(1)
                .enableCrop(true)
                .isCamera(true)// 是否显示拍照按钮
        //        .circleDimmedLayer(true)// 是否圆形裁剪
                .selectionMode(PictureConfig.SINGLE)
                .compress(true)// 是否压缩
                .withAspectRatio(3, 2)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(false)// 是否显示uCrop工具栏，默认不显示
         //       .freeStyleCropEnabled(false)// 裁剪框是否可拖拽
          //      .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
           //     .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .minSelectNum(1)
                .imageSpanCount(4)
                .selectionMode(PictureConfig.MULTIPLE)
                .forResult(PictureConfig.CHOOSE_REQUEST)

        //            .openGallery(chooseMode)// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
        //            .imageSpanCount(4)// 每行显示个数
        //            .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
        //            .previewImage(true)// 是否可预览图片
        ////            .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
        //            .compress(true)// 是否压缩
        //            .enableCrop(true)// 是否裁剪
        ////            .synOrAsy(true)//同步true或异步false 压缩 默认同步
        //            .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
        //            .freeStyleCropEnabled(false)// 裁剪框是否可拖拽
        //            .circleDimmedLayer(true)// 是否圆形裁剪
        ////            .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
        ////            .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
        //            .openClickSound(false)// 是否开启点击声音
        ////            .isDragFrame(false)// 是否可拖动裁剪框(固定)
        //            .minimumCompressSize(100)// 小于100kb的图片不压缩
        //            .selectionMedia(selectList)
        //            .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
        //
    }

    private fun takePic() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(1)
                .enableCrop(true)
                .isCamera(true)// 是否显示拍照按钮
//                .circleDimmedLayer(true)// 是否圆形裁剪
                .selectionMode(PictureConfig.SINGLE)
                .compress(true)// 是否压缩
                .withAspectRatio(3, 2)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
 //               .hideBottomControls(false)// 是否显示uCrop工具栏，默认不显示
 //               .freeStyleCropEnabled(false)// 裁剪框是否可拖拽
//                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
//                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .minSelectNum(1)
                .imageSpanCount(4)
                .selectionMode(PictureConfig.MULTIPLE)
                .forResult(PictureConfig.CHOOSE_REQUEST)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                PictureConfig.CHOOSE_REQUEST -> {
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data)
//                    for (media in selectList) {
                    if (selectList.isNotEmpty()) {
                        //photo.setImageBitmap(BitmapFactory.decodeFile(media.getCompressPath()));
                        var path = selectList[0].compressPath
                       // upLoadHeardToOss(path)
                       if (state==1) {
                           ImageLoader.loadImageLocal(this, zhengmian, selectList[0].compressPath)
                       }else if(state==2){
                           ImageLoader.loadImageLocal(this, beimian, selectList[0].compressPath)
                       }
//                    }
                }
            }
        }

    }}
}
