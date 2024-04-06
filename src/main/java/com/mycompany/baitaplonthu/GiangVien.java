/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.baitaplonthu;

/**
 *
 * @author PC
 */
public class GiangVien {
    private String MSGV;
    private String hoTen;
    private String hocHam;
    private String hocVi;

    public GiangVien() {
    }

    public GiangVien(String MSGV, String hoTen, String hocHam, String hocVi) {
        this.MSGV = MSGV;
        this.hoTen = hoTen;
        this.hocHam = hocHam;
        this.hocVi = hocVi;
    }

    /**
     * @return the hoTen
     */
    public String getHoTen() {
        return hoTen;
    }

    /**
     * @param hoTen the hoTen to set
     */
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    /**
     * @return the hocHam
     */
    public String getHocHam() {
        return hocHam;
    }

    /**
     * @param hocHam the hocHam to set
     */
    public void setHocHam(String hocHam) {
        this.hocHam = hocHam;
    }

    /**
     * @return the hocVi
     */
    public String getHocVi() {
        return hocVi;
    }

    /**
     * @param hocVi the hocVi to set
     */
    public void setHocVi(String hocVi) {
        this.hocVi = hocVi;
    }

    public void nhapGv() {
        System.out.printf("Ten Giang Vien= ");
        this.hoTen = KtInput.getInput();
        System.out.printf("Hoc Ham= ");
        this.hocHam = KtInput.getInput();
        System.out.printf("Hoc Vi= ");
        this.hocVi = KtInput.getInput();

    }

    public void hienThi() {
        System.out.printf("Ma ID: %s\n", this.MSGV);
        System.out.printf("Ten giang vien: %s\n", this.hoTen);
        System.out.printf("Hoc ham: %s\n", this.hocHam);
        System.out.printf("Hoc vi: %s\n", this.hocVi);

    }

    /**
     * @return the MSGV
     */
    public String getMSGV() {
        return MSGV;
    }

    /**
     * @param MSGV the MSGV to set
     */
    public void setMSGV(String MSGV) {
        this.MSGV = MSGV;
    }
}
