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
public class QuanLyGiangVien {
    private ArrayList<GiangVien> ds = new ArrayList<>();

    public QuanLyGiangVien() {
    }

    public QuanLyGiangVien(ArrayList<GiangVien> ds) {
        this.ds = ds;
    }
    
    public GiangVien findGvById(String id) {
        for (int i = 0; i < ds.size(); i++) {
            if (id.equals(ds.get(i).getMSGV())) {
                return ds.get(i);
            }
        }
        return null;
    }
    
    public GiangVien findGvByName(String name) {
        for (int i = 0; i < ds.size(); i++) {
            if (name.equals(ds.get(i).getHoTen())) {
                return ds.get(i);
            }
        }
        return null;
    }

    public void hienThiDS() {
        ds.forEach(h -> h.hienThi());
    }

    public ArrayList<GiangVien> getDs() {
        return ds;
    }

    public void setDs(ArrayList<GiangVien> ds) {
        this.ds = ds;
    }

    

}
