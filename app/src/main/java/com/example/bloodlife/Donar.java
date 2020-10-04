package com.example.bloodlife;

public class Donar {
    private String Name;
    private String blgrp;
    private String units;
    private String hos;
    private Integer pno;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBlgrp() {
        return blgrp;
    }

    public void setBlgrp(String blgrp) {
        this.blgrp = blgrp;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getHos() {
        return hos;
    }

    public void setHos(String hos) {
        this.hos = hos;
    }

    public Integer getPno() {
        return pno;
    }

    public void setPno(Integer pno) {
        this.pno = pno;
    }

    public Donar() {
    }
}
