package com.ahghorab.xenonet.web.rest.vm.pojo.terminationPort;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TerminationPortOptionPOJO {
    @JsonProperty("option")
    private String option;

    @JsonProperty("value")
    private String value;


    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
