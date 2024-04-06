/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.baitaplonthu;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Tus
 */
public class BaoCaoCoTyLeDaoVan extends BaoCao {
    private double tyLeDaoVan;

    public BaoCaoCoTyLeDaoVan() {
        super();
    }

    public BaoCaoCoTyLeDaoVan(String maBaoCao, String tenBaoCao, String chuoiLink, Date ngayBaoCao,
            ArrayList<SinhVien> dssv, String tenGiangVienHuongDan, double diemBaoCao, double tyLeDaoVan) {
        super(maBaoCao, tenBaoCao, chuoiLink, ngayBaoCao, dssv, tenGiangVienHuongDan, diemBaoCao);
        this.tyLeDaoVan = tyLeDaoVan;
    }

    @Override
    public void input(QuanLySinhVien qlsv, QuanLyGiangVien qlgv, QuanLyBaoCao qlbc) throws ParseException {
        super.input(qlsv, qlgv, qlbc);
        if (diemBaoCao != -1) {
            System.out.println("Nhap ti le dao van: ");
            setTyLeDaoVan(Double.parseDouble(KtInput.getInput()));
        }
    }

    @Override
    public void suaBaoCao(int type, QuanLyGiangVien qlgv, QuanLySinhVien qlsv) throws ParseException {
        super.suaBaoCao(type, qlgv, qlsv);
        if (diemBaoCao != -1) {
            switch (type) {
                case 1:// sua danh sach diem
                    System.out.print("Nhap ti le dao van: ");
                    setTyLeDaoVan(Double.parseDouble(KtInput.getInput()));
            }
        }
    }

    @Override
    public void hienThi() {
        super.hienThi();
        System.out.println("Ty le dao van: " + tyLeDaoVan);
    }

    /**
     * @return the tyLeDaoVan
     */
    public double getTyLeDaoVan() {
        return tyLeDaoVan;
    }

    /**
     * @param tyLeDaoVan the tyLeDaoVan to set
     */
    public void setTyLeDaoVan(double tyLeDaoVan) {
        while (tyLeDaoVan < 0 || tyLeDaoVan > 100) {
            System.out.print("Nhap ti le dao van: ");
            tyLeDaoVan = Double.parseDouble(KtInput.getInput());
        }
        this.tyLeDaoVan = tyLeDaoVan;
    }

}
