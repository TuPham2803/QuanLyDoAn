/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.baitaplonthu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PC
 */
public class BaoCaoThucTapTotNghiep extends BaoCao {

    private String danhgiaDoanhNghiep;

    public BaoCaoThucTapTotNghiep() {
    }

    public BaoCaoThucTapTotNghiep(String maBaoCao, String tenBaoCao, String chuoiLink, Date ngayBaoCao,
            ArrayList<SinhVien> dssv, String tenGiangVienHuongDan, double diemBaoCao, String danhgiaDoanhNghiep) {
        super(maBaoCao, tenBaoCao, chuoiLink, ngayBaoCao, dssv, tenGiangVienHuongDan, diemBaoCao);
        this.danhgiaDoanhNghiep = danhgiaDoanhNghiep;
    }
    public BaoCaoThucTapTotNghiep newInstance(BaoCaoThucTapTotNghiep bc) throws ParseException{
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return new BaoCaoThucTapTotNghiep(bc.maBaoCao, bc.tenBaoCao, bc.chuoiLink, df.parse(df.format(bc.ngayBaoCao)),
                bc.dssv, bc.tenGiangVienHuongDan, bc.diemBaoCao, bc.danhgiaDoanhNghiep);
    }
    @Override
    public void input(QuanLySinhVien qlsv, QuanLyGiangVien qlgv, QuanLyBaoCao qlbc) throws ParseException {
        super.input(qlsv, qlgv, qlbc); // Generated from
        // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        if (diemBaoCao != -1) {
            System.out.printf("Thong tin danh gia cua doanh nghiep: ");
            setDanhgiaDoanhNghiep(KtInput.getInput());
        } else {
            System.out.println("Chua duoc cham!");
        }
    }

    @Override
    public void suaBaoCao(int type, QuanLyGiangVien qlgv, QuanLySinhVien qlsv) throws ParseException {
        super.suaBaoCao(type, qlgv, qlsv);
        if (diemBaoCao != -1) {
            switch (type) {
                case 1:// sua danh sach diem
                    if (diemBaoCao != -1) {
                        System.out.printf("Thong tin danh gia cua doanh nghiep: ");
                        setDanhgiaDoanhNghiep(KtInput.getInput());
                    }
            }
        }
    }

    /**
     * @return the danhgiaDoanhNghiep
     */
    public String getDanhgiaDoanhNghiep() {
        return danhgiaDoanhNghiep;
    }

    /**
     * @param danhgiaDoanhNghiep the danhgiaDoanhNghiep to set
     */
    public void setDanhgiaDoanhNghiep(String danhgiaDoanhNghiep) {
        this.danhgiaDoanhNghiep = danhgiaDoanhNghiep;
    }

}
