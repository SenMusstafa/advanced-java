package com.etiya.campus.aj.common;

public class Computer  extends Product{
    private String cpu;
    private String ram;
    private String disc;
    public Computer(String name) {
        super(name);
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }
}
