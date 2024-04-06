/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.baitaplonthu;

/**
 *
 * @author PC
 */
public class SinhVien {

    private String MSSV;
    private String khoaHoc;
    private String gioiTinh;
    private String namSinh;
    private String hoTen;
    private String chuyenNganh;

    public SinhVien() {
    }

    public SinhVien(String MSSV, String khoaHoc, String gioiTinh, String namSinh, String hoTen, String chuyenNganh) {
        this.MSSV = MSSV;
        this.khoaHoc = khoaHoc;
        this.gioiTinh = gioiTinh;
        this.namSinh = namSinh;
        this.hoTen = hoTen;
        this.chuyenNganh = chuyenNganh;
    }

    // public void nhapSv() {
    //     Scanner sc = new Scanner(System.in);
    //     System.out.printf("MSSV= ");
    //     this.MSSV = KtInput.getInput();
    //     System.out.printf("Ho ten= ");
    //     this.MSSV = KtInput.getInput();
    //     System.out.printf("Khoa hoc= ");
    //     this.MSSV = KtInput.;
    //     System.out.printf("Gioi tinh= ");
    //     this.MSSV = KtInput.;
    //     System.out.printf("Nam sinh= ");
    //     this.MSSV = KtInput.;
    //     System.out.printf("Chuyen nganh= ");
    //     this.MSSV = KtInput.;
    // }

    public void hienThi() {
        System.out.printf("ma so sinh vien: %s\n", this.MSSV);
        System.out.printf("ho ten: %s\n", this.hoTen);
        System.out.printf("khoa hoc: %s\n", this.khoaHoc);
        System.out.printf("gioi tinh: %s\n", this.gioiTinh);
        System.out.printf("nam sinh: %s\n", this.namSinh);
        System.out.printf("chuyen nganh: %s\n", this.chuyenNganh);

    }

    /**
     * @return the MSSV
     */
    public String getMSSV() {
        return MSSV;
    }

    /**
     * @param MSSV the MSSV to set
     */
    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    /**
     * @return the khoaHoc
     */
    public String getKhoaHoc() {
        return khoaHoc;
    }

    /**
     * @param khoaHoc the khoaHoc to set
     */
    public void setKhoaHoc(String khoaHoc) {
        this.khoaHoc = khoaHoc;
    }

    /**
     * @return the gioiTinh
     */
    public String getGioiTinh() {
        return gioiTinh;
    }

    /**
     * @param gioiTinh the gioiTinh to set
     */
    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    /**
     * @return the namSinh
     */
    public String getNamSinh() {
        return namSinh;
    }

    /**
     * @param namSinh the namSinh to set
     */
    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
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
     * @return the chuyenNganh
     */
    public String getChuyenNganh() {
        return chuyenNganh;
    }

    /**
     * @param chuyenNganh the chuyenNganh to set
     */
    public void setChuyenNganh(String chuyenNganh) {
        this.chuyenNganh = chuyenNganh;
    }

}
