/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.baitaplonthu;

import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class QuanLySinhVien {

    private ArrayList<SinhVien> ds = new ArrayList<>();

    public QuanLySinhVien() {
    }

    public QuanLySinhVien(ArrayList<SinhVien> ds) {
        this.ds = ds;
    }

    public SinhVien findSvById(String id) {
        for (int i = 0; i < ds.size(); i++) {
            if (id.equals(ds.get(i).getMSSV())) {
                return ds.get(i);
            }
        }
        return null;
    }

    public void hienThiDS() {
        ds.forEach(h -> h.hienThi());
    }

    public ArrayList<SinhVien> getDs() {
        return ds;
    }

    public void setDs(ArrayList<SinhVien> ds) {
        this.ds = ds;
    }
}
