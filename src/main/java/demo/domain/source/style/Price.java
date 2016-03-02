package demo.domain.source.style;

import com.fasterxml.jackson.annotation.JsonProperty;
import demo.domain.target.AttributableEntity;

public class Price extends AttributableEntity {

    private static final String[] NUMERIC = {
            "baseMsrp",
    };

    private static final String[] NOMINAL_OPTIONAL = {
            "baseInvoice",
            "deliveryCharges",
            "usedTmvRetail",
            "usedPrivateParty",
            "usedTradeIn",
            "tmvRecommendedRating",
    };

    @JsonProperty("baseMSRP")
    private Integer baseMsrp;
    private Integer baseInvoice;
    private Integer deliveryCharges;
    private Integer usedTmvRetail;
    private Integer usedPrivateParty;
    private Integer usedTradeIn;
    private boolean estimateTmv;
    private Integer tmvRecommendedRating;

    public Integer getBaseMsrp() {
        return baseMsrp;
    }

    public void setBaseMsrp(Integer baseMsrp) {
        this.baseMsrp = baseMsrp;
    }

    public Integer getBaseInvoice() {
        return baseInvoice;
    }

    public void setBaseInvoice(Integer baseInvoice) {
        this.baseInvoice = baseInvoice;
    }

    public Integer getDeliveryCharges() {
        return deliveryCharges;
    }

    public void setDeliveryCharges(Integer deliveryCharges) {
        this.deliveryCharges = deliveryCharges;
    }

    public Integer getUsedTmvRetail() {
        return usedTmvRetail;
    }

    public void setUsedTmvRetail(Integer usedTmvRetail) {
        this.usedTmvRetail = usedTmvRetail;
    }

    public Integer getUsedPrivateParty() {
        return usedPrivateParty;
    }

    public void setUsedPrivateParty(Integer usedPrivateParty) {
        this.usedPrivateParty = usedPrivateParty;
    }

    public Integer getUsedTradeIn() {
        return usedTradeIn;
    }

    public void setUsedTradeIn(Integer usedTradeIn) {
        this.usedTradeIn = usedTradeIn;
    }

    public boolean isEstimateTmv() {
        return estimateTmv;
    }

    public void setEstimateTmv(boolean estimateTmv) {
        this.estimateTmv = estimateTmv;
    }

    public Integer getTmvRecommendedRating() {
        return tmvRecommendedRating;
    }

    public void setTmvRecommendedRating(Integer tmvRecommendedRating) {
        this.tmvRecommendedRating = tmvRecommendedRating;
    }

    @Override
    public String[] getNumericAbsoluteNames() {
        return NUMERIC;
    }

    @Override
    public String[] getNominalOptionalNames() {
        return NOMINAL_OPTIONAL;
    }
}
