package com.imooc.test;

/**
 * @author Ace
 * @create 2020-09-22
 */
public enum OperateEnum {

    /**
     * 上报操作
     */
    SHANGBAO("SHANGBAO","上报操作"),
    /**
     * 更新/保存操作
     */
    UPDATE("UPDATE","更新/保存操作"),
    /**
     * 历史数据
     */
    HISTORY_DATA("HISTORY_DATA","历史数据"),
    /**
     * 审核操作
     */
    SHENHE("SHENHE","审核操作"),
    /**
     * 撤回操作
     */
    CHEHUI("CHEHUI","撤回操作"),
    /**
     *申请撤回操作
     */
    SHENQINCH("SHENQINCH","申请撤回操作"),
    /**
     * 取消申请操作
     */
    QUXIAOSQ("QUXIAOSQ","取消申请操作"),
    /**
     *撤销操作
     */
    CHEXIAO("CHEXIAO","撤销操作"),
    /**
     * 取消撤销操作
     */
    QUXIAOCX("QUXIAOCX","取消撤销操作"),
    /**
     * 退回操作
     */
    TUIHUI("TUIHUI","退回操作"),
    /**
     * 同意操作
     */
    TONGYI("S","同意操作"),
    /**
     * 不同意操作
     */
    BUTONGYI("BUTONGYI","不同意操作"),


    /**
     * 成交公示_变更操作： 国有成交公示、集体成交公示、批前公示
     */
    CJGS_BIANGENG("CJGS_BIANGENG","成交公示_变更操作"),
    /**
     * 变更操作
     */
    BIANGENG("BIANGENG","变更操作");

    private String type;
    private String name;

    private OperateEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

}
