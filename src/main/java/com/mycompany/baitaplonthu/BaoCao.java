/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.baitaplonthu;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PC
 */
public class BaoCao {

    protected String maBaoCao;
    protected String tenBaoCao;
    protected String chuoiLink;
    protected Date ngayBaoCao;
    protected ArrayList<SinhVien> dssv;
    protected String tenGiangVienHuongDan;
    protected double diemBaoCao;

    public BaoCao() {
    }

    public BaoCao(String maBaoCao, String tenBaoCao, String chuoiLink, Date ngayBaoCao, ArrayList<SinhVien> dssv,
            String tenGiangVienHuongDan, double diemBaoCao) {
        this.maBaoCao = maBaoCao;
        this.tenBaoCao = tenBaoCao;
        this.chuoiLink = chuoiLink;
        this.ngayBaoCao = ngayBaoCao;
        this.dssv = dssv;
        this.tenGiangVienHuongDan = tenGiangVienHuongDan;
        this.diemBaoCao = diemBaoCao;
    }

    public void hienThi() {
        System.out.println("============================================================");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        System.out.printf("Ma bao cao: %s\n", this.maBaoCao);
        System.out.printf("Ten bao cao: %s\n", this.tenBaoCao);
        System.out.printf("Chuoi link: %s\n", this.chuoiLink);
        Date ngay = null;
        try {
            ngay = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0001");
        } catch (ParseException exception) {
            exception.getMessage();
        }
        if (ngayBaoCao.compareTo(ngay) == 0)
            System.out.println("Ngay bao cao: Chua duoc lap hoi dong cham.");
        else
            System.out.printf("Ngay bao cao: %s\n", formatter.format(this.ngayBaoCao)); // 12/09/2021
        System.out.println("Danh sach sinh vien:");
        dssv.forEach(sv -> System.out.println(sv.getMSSV()));
        System.out.printf("Ten gv: %s\n", this.tenGiangVienHuongDan);
        if (diemBaoCao == -1)
            System.out.println("Diem: bao cao chua duoc cham");
        else
            System.out.printf("Diem: %s\n", this.diemBaoCao);
    }

    public void input(QuanLySinhVien qlsv, QuanLyGiangVien qlgv, QuanLyBaoCao qlbc) throws ParseException {
        ArrayList<BaoCao> dsbc = qlbc.getDs();
        String maMoi = dsbc.get(dsbc.size() - 1).maBaoCao;
        maMoi = Integer.parseInt(maMoi) + 1 + "";
        setMaBaoCao(maMoi);
        System.out.printf("Ten bao cao: ");
        setTenBaoCao(KtInput.getInput().trim());
        System.out.printf("Chuoi link: ");
        setChuoiLink(KtInput.getInput());
        setNgayBaoCao(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0001"));
        System.out.printf("Nhap so luong sinh vien: ");
        int soLuong = parseInt(KtInput.getInput());
        while (soLuong < 1 || soLuong > 2) {
            System.out.printf("Nhap lai ( 1<=s o luong <=2: ");
            soLuong = parseInt(KtInput.getInput());
        }
        dssv = new ArrayList<>();
        for (int i = 1; i <= soLuong; i++) {
            SinhVien sv = null;
            while (sv == null) {
                System.out.printf("Nhap MSSV %d: ", i);
                String idSV = KtInput.getInput();
                sv = qlsv.findSvById(idSV);
                if (sv == null) {
                    System.out.printf("Khong co sinh vien trong danh sach!");
                }
            }

            dssv.add(sv);
        }

        GiangVien gv = null;
        while (gv == null) {
            System.out.printf("Nhap ID giang vien huong dan: ");
            String idGv = KtInput.getInput();
            gv = qlgv.findGvById(idGv);
            if (gv == null) {
                System.out.printf("Khong co giang vien trong danh sach!");
            }
        }
        tenGiangVienHuongDan = gv.getHoTen();
        diemBaoCao = -1;
    }

    public void suaBaoCao(int type, QuanLyGiangVien qlgv, QuanLySinhVien qlsv) throws ParseException {
        if (diemBaoCao == -1) {
            switch (type) {
                case 1:// 1.Tên
                    System.out.printf("Ten bao cao: ");
                    setTenBaoCao(KtInput.getInput().trim());
                    break;
                case 2:// Link
                    System.out.printf("Chuoi link: ");
                    setChuoiLink(KtInput.getInput());
                    break;
                case 3:// Sinh viên Thực Hiện
                    System.out.printf("Nhap so luong sinh vien: ");
                    int soLuong = parseInt(KtInput.getInput());
                    while (soLuong < 1 || soLuong > 2) {
                        System.out.printf("Nhap lai ( 1<=s o luong <=2: ");
                        soLuong = parseInt(KtInput.getInput());
                    }
                    dssv = new ArrayList<>();
                    for (int i = 1; i <= soLuong; i++) {
                        SinhVien sv = null;
                        while (sv == null) {
                            System.out.printf("Nhap MSSV %d: ", i);
                            String idSV = KtInput.getInput();
                            sv = qlsv.findSvById(idSV);
                            if (sv == null) {
                                System.out.printf("Khong co sinh vien trong danh sach!");
                            }
                        }
                        dssv.add(sv);
                    }
                    break;
                case 4:// GV hướng dẫn
                    GiangVien gv = null;
                    while (gv == null) {
                        System.out.printf("Nhap ID giang vien huong dan: ");
                        String idGv = KtInput.getInput();
                        gv = qlgv.findGvById(idGv);

                        if (gv == null) {
                            System.out.printf("Khong co giang vien trong danh sach!");
                        } else if (gv.getHoTen().equals(tenGiangVienHuongDan)) {
                            System.out.println("Giang vien huong dan cu!");
                            gv = null;
                        }
                    }
                    tenGiangVienHuongDan = gv.getHoTen();
                    break;
            }
        }

    }

    /**
     * @return the maBaoCao
     */
    public String getMaBaoCao() {
        return maBaoCao;
    }

    /**
     * @param maBaoCao the maBaoCao to set
     */
    public void setMaBaoCao(String maBaoCao) {
        this.maBaoCao = maBaoCao;
    }

    /**
     * @return the tenBaoCao
     */
    public String getTenBaoCao() {
        return tenBaoCao;
    }

    /**
     * @param tenBaoCao the tenBaoCao to set
     */
    public void setTenBaoCao(String tenBaoCao) {
        this.tenBaoCao = tenBaoCao;
    }

    /**
     * @return the chuoiLink
     */
    public String getChuoiLink() {
        return chuoiLink;
    }

    /**
     * @param chuoiLink the chuoiLink to set
     */
    public void setChuoiLink(String chuoiLink) {
        this.chuoiLink = chuoiLink;
    }

    /**
     * @return the ngayBaoCao
     */
    public Date getNgayBaoCao() {
        return ngayBaoCao;
    }

    /**
     * @param ngayBaoCao the ngayBaoCao to set
     */
    public void setNgayBaoCao(Date ngayBaoCao) {
        this.ngayBaoCao = ngayBaoCao;
    }

    /**
     * @return the dssv
     */
    public ArrayList<SinhVien> getDssv() {
        return dssv;
    }

    /**
     * @param dssv the dssv to set
     */
    public void setDssv(ArrayList<SinhVien> dssv) {
        this.dssv = dssv;
    }

    /**
     * @return the tenGiangVienHuongDan
     */
    public String getTenGiangVienHuongDan() {
        return tenGiangVienHuongDan;
    }

    /**
     * @param tenGiangVienHuongDan the tenGiangVienHuongDan to set
     */
    public void setTenGiangVienHuongDan(String tenGiangVienHuongDan) {
        this.tenGiangVienHuongDan = tenGiangVienHuongDan;
    }

    /**
     * @return the diemBaoCao
     */
    public double getDiemBaoCao() {
        return diemBaoCao;
    }

    /**
     * @param diemBaoCao the diemBaoCao to set
     */
    public void setDiemBaoCao(double diemBaoCao) {
        this.diemBaoCao = diemBaoCao;
    }
}
