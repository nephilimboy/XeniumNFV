package com.ahghorab.xenonet.web.rest.vm;

//public id?: number,
//public key?: string,
//public srcEntityKey?: string,
//public destEntityKey?: string

public class DiagramEntityConnectionsVM {
    private Integer id;
    private String key;
    private String srcEntityKey;
    private String destEntityKey;

    public DiagramEntityConnectionsVM(){

    }

    public DiagramEntityConnectionsVM(Integer id, String key, String srcEntityKey, String destEntityKey) {
        this.id = id;
        this.key = key;
        this.srcEntityKey = srcEntityKey;
        this.destEntityKey = destEntityKey;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSrcEntityKey() {
        return srcEntityKey;
    }

    public void setSrcEntityKey(String srcEntityKey) {
        this.srcEntityKey = srcEntityKey;
    }

    public String getDestEntityKey() {
        return destEntityKey;
    }

    public void setDestEntityKey(String destEntityKey) {
        this.destEntityKey = destEntityKey;
    }
}
