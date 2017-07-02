package com.heitian.ssm.model;

public class BehaviorAna extends BehaviorAnaKey {
    private String standard;

    private String buTongZhuTiLiuLanShiChang;

    private String buTongZhuTiLiuLanCiShu;

    private String gaoPinDianJiShiDuan;

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard == null ? null : standard.trim();
    }

    public String getBuTongZhuTiLiuLanShiChang() {
        return buTongZhuTiLiuLanShiChang;
    }

    public void setBuTongZhuTiLiuLanShiChang(String buTongZhuTiLiuLanShiChang) {
        this.buTongZhuTiLiuLanShiChang = buTongZhuTiLiuLanShiChang == null ? null : buTongZhuTiLiuLanShiChang.trim();
    }

    public String getBuTongZhuTiLiuLanCiShu() {
        return buTongZhuTiLiuLanCiShu;
    }

    public void setBuTongZhuTiLiuLanCiShu(String buTongZhuTiLiuLanCiShu) {
        this.buTongZhuTiLiuLanCiShu = buTongZhuTiLiuLanCiShu == null ? null : buTongZhuTiLiuLanCiShu.trim();
    }

    public String getGaoPinDianJiShiDuan() {
        return gaoPinDianJiShiDuan;
    }

    public void setGaoPinDianJiShiDuan(String gaoPinDianJiShiDuan) {
        this.gaoPinDianJiShiDuan = gaoPinDianJiShiDuan == null ? null : gaoPinDianJiShiDuan.trim();
    }
}