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
public class BaoCaoKhoaLuan extends BaoCaoCoTyLeDaoVan {

    private String danhGiacuagv;
    private ArrayList<GiangVien> dsThanhVienBaoVeKhoaLuan;
    private ArrayList<Double> dsDiem;

    public BaoCaoKhoaLuan() {
        super(null, null, null, null, null, null, 0, 0);
    }

    public BaoCaoKhoaLuan(String maBaoCao, String tenBaoCao, String chuoiLink, Date ngayBaoCao,
            ArrayList<SinhVien> dssv, String tenGiangVienHuongDan, double diemBaoCao, double tyLeKiemTraDaoVan,
            String danhGiacuagv, ArrayList<GiangVien> dsThanhVienBaoVeKhoaLuan, ArrayList<Double> dsDiem) {
        super(maBaoCao, tenBaoCao, chuoiLink, ngayBaoCao, dssv, tenGiangVienHuongDan, diemBaoCao, tyLeKiemTraDaoVan);
        this.danhGiacuagv = danhGiacuagv;
        this.dsThanhVienBaoVeKhoaLuan = dsThanhVienBaoVeKhoaLuan;
        this.dsDiem = dsDiem;
    }

    public BaoCaoKhoaLuan newInstance(BaoCaoKhoaLuan bc) throws ParseException{
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<Double> dsDiemMoi= new ArrayList<>();
        bc.dsDiem.forEach(diem->dsDiemMoi.add(diem));
        return new BaoCaoKhoaLuan(bc.maBaoCao, bc.tenBaoCao, bc.chuoiLink, df.parse(df.format(bc.ngayBaoCao)),
                bc.dssv, bc.tenGiangVienHuongDan, bc.diemBaoCao, bc.getTyLeDaoVan(), bc.danhGiacuagv,
                bc.dsThanhVienBaoVeKhoaLuan, dsDiemMoi);
    }

    @Override
    public void hienThi() {
        super.hienThi();
        System.out.printf("Danh gia cua GV: %s\n", this.danhGiacuagv);
        System.out.printf("Danh sach GV tham gia: \n");
        dsThanhVienBaoVeKhoaLuan.forEach(gv -> System.out.printf("%s\n", gv.getHoTen()));
    }

    @Override
    public void input(QuanLySinhVien qlsv, QuanLyGiangVien qlgv, QuanLyBaoCao qlbc) throws ParseException {
        super.input(qlsv, qlgv, qlbc);
        dsThanhVienBaoVeKhoaLuan = new ArrayList<>();
        danhGiacuagv = "Danh gia giao vien";
        dsDiem = new ArrayList<>();
    }

    public boolean hasDiem() {
        return !dsDiem.isEmpty();
    }

    public void hienThiDSDiem() {
        System.out.printf("Ma bao cao: %s\n", this.maBaoCao);

        if (diemBaoCao == -1) {
            System.out.println("Bao cao khoa luan nay chua co diem");
        } else {
            for (int i = 0; i < this.dsThanhVienBaoVeKhoaLuan.size(); i++) {
                System.out.printf("Diem cua giang vien thu %d: %.2f\n", i + 1, this.dsDiem.get(i));
            }
            System.out.printf("Diem trung binh: %.2f\n", diemBaoCao);
        }

    }

    public void suaDiemBaoCaoKhoaLuan() {
        double diemNhap;
        int vitri = -1;

        do {
            System.out.print("Nhap vi tri sua diem (1,2,3,...): ");
            vitri = KtInput.getInputNumber();
            if (vitri > dsDiem.size() || vitri <= 0) {
                System.out.println("Khong co vi tri nhu vay");
            }
        } while (vitri > dsDiem.size() || vitri <= 0);

        do {
            System.out.print("Nhap diem: ");
            diemNhap = KtInput.getInputDiem();
            if (diemNhap < 0 || diemNhap > 10) {
                System.out.println("Sai diem");
            }
        } while (diemNhap < 0 || diemNhap > 10);

        dsDiem.set(vitri - 1, diemNhap);
        Double tongDiem = 0.0;
        for (int i = 0; i < dsDiem.size(); i++)
            tongDiem += dsDiem.get(i);
        diemBaoCao = tongDiem / dsDiem.size();
        System.out.println("Sua thanh cong");

    }

    @Override
    public void suaBaoCao(int type, QuanLyGiangVien qlgv, QuanLySinhVien qlsv) throws ParseException {
        super.suaBaoCao(type, qlgv, qlsv);
        if (diemBaoCao != -1) {
            switch (type) {
                case 2:// sua danh sach diem
                    hienThiDSDiem();
                    suaDiemBaoCaoKhoaLuan();
                    break;
                case 3:// sua danh gia cua giang vien
                    System.out.println("Nhap danh gia cua giang vien");
                    danhGiacuagv = KtInput.getInput();
                    break;
            }
        }
    }

    /**
     * @return the danhGiacuagv
     */
    public String getDanhGiacuagv() {
        return danhGiacuagv;
    }

    /**
     * @param danhGiacuagv the danhGiacuagv to set
     */
    public void setDanhGiacuagv(String danhGiacuagv) {
        this.danhGiacuagv = danhGiacuagv;
    }

    /**
     * @return the dsThanhVienBaoVeKhoaLuan
     */
    public ArrayList<GiangVien> getDsThanhVienBaoVeKhoaLuan() {
        return dsThanhVienBaoVeKhoaLuan;
    }

    /**
     * @param dsThanhVienBaoVeKhoaLuan the dsThanhVienBaoVeKhoaLuan to set
     */
    public void setDsThanhVienBaoVeKhoaLuan(ArrayList<GiangVien> dsThanhVienBaoVeKhoaLuan) {
        this.dsThanhVienBaoVeKhoaLuan = dsThanhVienBaoVeKhoaLuan;
    }

    /**
     * @return the dsDiem
     */
    public ArrayList<Double> getDsDiem() {
        return dsDiem;
    }

    /**
     * @param dsDiem the dsDiem to set
     */
    public void setDsDiem(ArrayList<Double> dsDiem) {
        this.dsDiem = dsDiem;
    }
}
