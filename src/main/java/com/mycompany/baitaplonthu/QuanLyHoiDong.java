/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.baitaplonthu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class QuanLyHoiDong {
    public static final SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
    public static final Scanner sc = new Scanner(System.in);
    private ArrayList<HoiDongBaoVeKhoaLuan> ds = new ArrayList<>();

    public QuanLyHoiDong() {
    }

    public QuanLyHoiDong(ArrayList<HoiDongBaoVeKhoaLuan> ds) {
        this.ds = ds;
    }

    public HoiDongBaoVeKhoaLuan findHoiDongById(String id) {
        for (int i = 0; i < ds.size(); i++) {
            if (id.equals(ds.get(i).getMaHoiDong())) {
                return ds.get(i);
            }
        }
        return null;
    }

    public void hienThiDS(QuanLyNhiemVu qlnv) {
        ds.forEach(h -> h.hienThi(qlnv));
    }

    public void hienThiDS(ArrayList<HoiDongBaoVeKhoaLuan> dsHoiDong) {
        for(int i = 0 ; i < dsHoiDong.size();i++){
            System.out.printf("%d| Ma hoi dong: %s\n",i+1,dsHoiDong.get(i).getMaHoiDong());
        }
    }
    public void hienThiDS(ArrayList<HoiDongBaoVeKhoaLuan> dsHoiDong,
    QuanLyNhiemVu qlnv) {
        dsHoiDong.forEach(h -> h.hienThi(qlnv));
    }

    public ArrayList<HoiDongBaoVeKhoaLuan> timKiemHoidongTheoNgayBaoVe(Date date) {
        ArrayList<HoiDongBaoVeKhoaLuan> dsHoiDongBaoVeKhoaLuan = new ArrayList<>();
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getNgayHoiDongLamViec().compareTo(date) == 0) {
                dsHoiDongBaoVeKhoaLuan.add(ds.get(i));
            }
        }
        if (dsHoiDongBaoVeKhoaLuan.isEmpty())
            return new ArrayList<>();
        else
            return dsHoiDongBaoVeKhoaLuan;
    }

    public ArrayList<HoiDongBaoVeKhoaLuan> timKiemHoidongTheoNgayBaoVe(Date start, Date end) {
        ArrayList<HoiDongBaoVeKhoaLuan> dsHoiDongBaoVeKhoaLuan = new ArrayList<>();
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getNgayHoiDongLamViec().compareTo(start) >= 0
                    && ds.get(i).getNgayHoiDongLamViec().compareTo(end) <= 0) {
                dsHoiDongBaoVeKhoaLuan.add(ds.get(i));
            }
        }
        if (dsHoiDongBaoVeKhoaLuan.isEmpty())
            return null;
        else
            return dsHoiDongBaoVeKhoaLuan;
    }

    public ArrayList<HoiDongBaoVeKhoaLuan> getDs() {
        return ds;
    }

    public void setDs(ArrayList<HoiDongBaoVeKhoaLuan> ds) {
        this.ds = ds;
    }

}
