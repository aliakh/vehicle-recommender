package demo.domain.source.style;

import com.fasterxml.jackson.annotation.JsonProperty;
import demo.domain.source.Entity;

public class OptionPrice extends Entity {

    @JsonProperty("baseMSRP")
    private Integer baseMsrp;
    private Integer baseInvoice;
    private boolean estimateTmv;

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

    public boolean isEstimateTmv() {
        return estimateTmv;
    }

    public void setEstimateTmv(boolean estimateTmv) {
        this.estimateTmv = estimateTmv;
    }
}
