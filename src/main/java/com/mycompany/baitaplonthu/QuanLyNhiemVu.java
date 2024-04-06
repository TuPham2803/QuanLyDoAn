/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.baitaplonthu;

import java.util.ArrayList;

/**
 *
 * @author Tus
 */
public class QuanLyNhiemVu {
    private ArrayList<NhiemVu> dsNhiemVu = new ArrayList<>();

    public QuanLyNhiemVu() {
    }

    public QuanLyNhiemVu(ArrayList<NhiemVu> ds) {
        this.dsNhiemVu = ds;
    }

    public void hienThiDS() {
        getDsNhiemVu().forEach(h -> h.hienThi());
    }

    public NhiemVu findNvByGvAndHd(String idGv, String idHd) {
        for (int i = 0; i < dsNhiemVu.size(); i++) {
            if (dsNhiemVu.get(i).getGiangVien().getMSGV().equals(idGv)
                    && dsNhiemVu.get(i).getHoiDong().getMaHoiDong().equals(idHd)) {
                return dsNhiemVu.get(i);
            }
        }
        return null;
    }

    /**
     * @return the dsNhiemVu
     */
    public ArrayList<NhiemVu> getDsNhiemVu() {
        return dsNhiemVu;
    }

    /**
     * @param dsNhiemVu the dsNhiemVu to set
     */
    public void setDsNhiemVu(ArrayList<NhiemVu> dsNhiemVu) {
        this.dsNhiemVu = dsNhiemVu;
    }
}
