/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.baitaplonthu;

/**
 *
 * @author Tus
 */
public class NhiemVu {
    private String vaiTro;
    private HoiDongBaoVeKhoaLuan hoiDong;
    private GiangVien giangVien;

    public NhiemVu(String vaiTro, GiangVien giangVien, HoiDongBaoVeKhoaLuan hoiDong) {
        this.vaiTro = vaiTro;
        this.hoiDong = hoiDong;
        this.giangVien = giangVien;
    }

    public void hienThi() {
        System.out.printf("Vai tro: %s\n", this.vaiTro);
        System.out.printf("Hoi dong khoa luan: %s\n", this.hoiDong.getMaHoiDong());
        System.out.printf("Giang Vien: %s\n", this.giangVien.getHoTen());
    }

    /**
     * @return the vaiTro
     */
    public String getVaiTro() {
        return vaiTro;
    }

    /**
     * @param vaiTro the vaiTro to set
     */
    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    /**
     * @return the hoiDong
     */
    public HoiDongBaoVeKhoaLuan getHoiDong() {
        return hoiDong;
    }

    /**
     * @param hoiDong the hoiDong to set
     */
    public void setHoiDong(HoiDongBaoVeKhoaLuan hoiDong) {
        this.hoiDong = hoiDong;
    }

    /**
     * @return the giangVien
     */
    public GiangVien getGiangVien() {
        return giangVien;
    }

    /**
     * @param giangVien the giangVien to set
     */
    public void setGiangVien(GiangVien giangVien) {
        this.giangVien = giangVien;
    }

}
