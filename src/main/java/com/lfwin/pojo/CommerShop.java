package com.lfwin.pojo;

import org.apache.ibatis.annotations.Mapper;

public class CommerShop {
    private String shopname;
    private String lfsignkey;
    private String storeId;
    private String alipayStoreId;
    private String sharingRuleId;
    private String profitSharing;
    private String profitProportion;
    private String autoSharing;
    private String sharingType;
    private String channel;

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getLfsignkey() {
        return lfsignkey;
    }

    public void setLfsignkey(String lfsignkey) {
        this.lfsignkey = lfsignkey;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getAlipayStoreId() {
        return alipayStoreId;
    }

    public void setAlipayStoreId(String alipayStoreId) {
        this.alipayStoreId = alipayStoreId;
    }

    public String getSharingRuleId() {
        return sharingRuleId;
    }

    public void setSharingRuleId(String sharingRuleId) {
        this.sharingRuleId = sharingRuleId;
    }

    public String getProfitSharing() {
        return profitSharing;
    }

    public void setProfitSharing(String profitSharing) {
        this.profitSharing = profitSharing;
    }

    public String getProfitProportion() {
        return profitProportion;
    }

    public void setProfitProportion(String profitProportion) {
        this.profitProportion = profitProportion;
    }

    public String getAutoSharing() {
        return autoSharing;
    }

    public void setAutoSharing(String autoSharing) {
        this.autoSharing = autoSharing;
    }

    public String getSharingType() {
        return sharingType;
    }

    public void setSharingType(String sharingType) {
        this.sharingType = sharingType;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
