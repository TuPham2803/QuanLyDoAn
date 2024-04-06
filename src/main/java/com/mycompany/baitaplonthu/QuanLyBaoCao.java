/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.baitaplonthu;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Tus
 */
public class QuanLyBaoCao implements QuanLyDanhSach {

    private ArrayList<BaoCao> ds = new ArrayList<>();

    public QuanLyBaoCao() {
    }

    public QuanLyBaoCao(ArrayList<BaoCao> ds) {
        this.ds = ds;
    }

    @Override
    public void xemThongTin() {
        ds.forEach(h -> h.hienThi());
    }

    public void xemThongTin(ArrayList<BaoCao> dsBaoCao) {
        dsBaoCao.forEach(h -> h.hienThi());
    }

    @Override
    public void suaThongTin(Object obj) {
        for(int i = 0; i < ds.size(); i++)
            if(ds.get(i).maBaoCao.equals(((BaoCao) obj).maBaoCao)){
                ds.set(i,(BaoCao) obj);
                break;
            }
    }

    @Override
    public void themThongTin(Object obj) {
        ds.add((BaoCao) obj);
    }

    @Override
    public void xoaThongTin(String id) {// removeBaoCao
        BaoCao bc = findBcById(id);
        if (bc instanceof BaoCaoKhoaLuan
                && !((BaoCaoKhoaLuan) bc).getDsThanhVienBaoVeKhoaLuan().isEmpty()) {
            bc.setTenBaoCao(bc.getTenBaoCao() + "_nghi");
        } else {
            ds.remove(bc);
        }
    }

    // public void
    public void hienThiDSTheoLoai(int loai) {
        switch (loai) {
            case 1 -> {
                for (BaoCao i : ds) {
                    if (i instanceof BaoCaoKhoaLuan && !i.getTenBaoCao().contains("_nghi")) {
                        i.hienThi();
                    }
                }
            }
            case 2 -> {
                for (BaoCao i : ds) {
                    if (i instanceof BaoCaoThucTapTotNghiep && !i.getTenBaoCao().contains("_nghi")) {
                        i.hienThi();
                    }
                }
            }
            case 3 -> {
                for (BaoCao i : ds) {
                    if (i instanceof BaoCaoDoAnNganh && !i.getTenBaoCao().contains("_nghi")) {
                        i.hienThi();
                    }
                }
            }
            default ->
                throw new AssertionError();
        }
    }

    public BaoCao findBcById(String id) {
        for (int i = 0; i < ds.size(); i++) {
            if (id.equals(ds.get(i).getMaBaoCao())) {
                return ds.get(i);
            }
        }
        return null;
    }

    public void sapXepBaoCaoTheoTen() {
        this.ds.sort((s1, s2) -> {
            String n1 = s1.getTenBaoCao().toUpperCase();
            String n2 = s2.getTenBaoCao().toUpperCase();

            return n1.compareTo(n2);
        });
    }

    public void sapXepBaoCaoTheoNgay() {
        this.ds.sort((s1, s2) -> {
            Date d1 = s1.getNgayBaoCao();
            Date d2 = s2.getNgayBaoCao();

            return d1.compareTo(d2);
        });
    }

    public ArrayList<BaoCao> timKiemTheoTen(String ten) {
        ArrayList<BaoCao> dsBaoCao = new ArrayList<>();
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getTenBaoCao().toUpperCase().contains(ten.toUpperCase())) {
                dsBaoCao.add(ds.get(i));
            }
        }
        if (dsBaoCao.isEmpty()) {
            return null;
        }
        return dsBaoCao;
    }

    public ArrayList<BaoCao> timKiemTheoNgay(Date start, Date end) {
        ArrayList<BaoCao> dsBaoCao = new ArrayList<>();
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getNgayBaoCao().compareTo(start) >= 0
                    && ds.get(i).getNgayBaoCao().compareTo(end) <= 0) {
                dsBaoCao.add(ds.get(i));
            }
        }
        if (dsBaoCao.isEmpty()) {
            return null;
        }
        return dsBaoCao;
    }

    public void xemDiemBaoCaoKhoaLuan() {
        ds.forEach(s -> {
            if (s instanceof BaoCaoKhoaLuan baoCaoKhoaLuan) {
                baoCaoKhoaLuan.hienThiDSDiem();
            }
        });
    }

    public ArrayList<BaoCao> getDs() {
        return ds;
    }

    public void setDs(ArrayList<BaoCao> ds) {
        this.ds = ds;
    }

}
