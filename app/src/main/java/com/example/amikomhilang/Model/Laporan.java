package com.example.amikomhilang.Model;

import java.io.Serializable;

public class Laporan implements Serializable {

    private String key;
    private String nama;
    private String nim;
    private String keluhan;
    private String idmhs;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getKeluhan() {
        return keluhan;
    }

    public void setKeluhan(String keluhan) {
        this.keluhan = keluhan;
    }

    @Override
    public String toString(){
        return " "+idmhs+"\n" +
                " "+nama+"\n" +
                " "+nim +"\n" +
                " "+keluhan;
    }

    public Laporan() {
    }

    public Laporan(String idmhs, String nama, String nim, String keluhan) {
        this.idmhs = idmhs;
        this.nim = nim;
        this.nama = nama;
        this.keluhan = keluhan;
    }

    public String getIdmhs() {
        return idmhs;
    }

    public void setIdmhs(String idmhs) {
        this.idmhs = idmhs;
    }
}
