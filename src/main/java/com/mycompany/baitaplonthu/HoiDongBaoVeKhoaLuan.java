/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.baitaplonthu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PC
 */
public class HoiDongBaoVeKhoaLuan {

    private String maHoiDong;
    private ArrayList<BaoCaoKhoaLuan> dsKhoaLuan;
    private Date ngayHoiDongLamViec;
    private ArrayList<GiangVien> dsGvThamGia;

    public HoiDongBaoVeKhoaLuan() {
    }

    public HoiDongBaoVeKhoaLuan(String maHoiDong, ArrayList<BaoCaoKhoaLuan> dsKhoaLuan, Date ngayHoiDongLamViec,
            ArrayList<GiangVien> dsGvThamGia) {
        this.maHoiDong = maHoiDong;
        this.dsKhoaLuan = dsKhoaLuan;
        this.ngayHoiDongLamViec = ngayHoiDongLamViec;
        this.dsGvThamGia = dsGvThamGia;
    }

    public boolean kiemTraGVHoiDong(GiangVien gv, ArrayList<String> tenGVHuongDan) {
        if (gv == null) {
            System.out.println("Khong tim thay giang vien");
            return false;
        } else if (tenGVHuongDan.contains(gv.getHoTen())) {
            System.out.printf("Trung ten giang vien huong dan cua bao cao %d \n",
                    tenGVHuongDan.indexOf(gv.getHoTen()) + 1);
            return false;
        } else {
            for (int i = 0; i < dsGvThamGia.size(); i++) {
                if (dsGvThamGia.get(i).getMSGV().equals(gv.getMSGV())) {
                    System.out.println("Giang vien da tham gia hoi dong");
                    return false;
                }
            }
        }
        return true;
    }

    public GiangVien themGiaoVienVaoHoiDong(String vaiTro, QuanLyGiangVien qlgv, ArrayList<String> tenGVHuongDan) {
        String idGV;
        GiangVien gv;
        do {
            System.out.print("Nhap id " + vaiTro + " : ");
            idGV = KtInput.getInput();
            gv = qlgv.findGvById(idGV);
            if (!kiemTraGVHoiDong(gv, tenGVHuongDan)) {
                gv = null;
            }
        } while (gv == null);
        return gv;
    }

    public boolean kiemTraBaoCaoCoHoiDong(BaoCao bc) {
        Date ngay = null;
        try {
            ngay = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/0001");
        } catch (Exception exception) {
            exception.getMessage();
        }
        return bc.ngayBaoCao.compareTo(ngay) != 0;
    }

    public void inputHoiDong(QuanLyBaoCao qlbc, QuanLyGiangVien qlgv, QuanLyNhiemVu qlnv, QuanLyHoiDong qlhd) {
        ArrayList<String> tenGVHuongDan = new ArrayList<>();
        dsKhoaLuan = new ArrayList<>();
        dsGvThamGia = new ArrayList<>();
        BaoCao bc = null;

        String maMoi = qlhd.getDs().get(qlhd.getDs().size() - 1).maHoiDong;
        maMoi = String.format("%03d", Integer.parseInt(maMoi) + 1);
        this.maHoiDong = maMoi;

        int soLuongBaoCao = -1;
        do {
            System.out.print("Nhap so luong bao cao can cham: ");
            soLuongBaoCao = KtInput.getInputNumber();
        } while (soLuongBaoCao <= 0);

        for (int i = 0; i < soLuongBaoCao; i++) {
            String maBaoCao;

            do {
                System.out.printf("Nhap ma bao cao thu %d:", i + 1);
                maBaoCao = KtInput.getInput();
                bc = qlbc.findBcById(maBaoCao);
                if (bc == null) {
                    System.out.println("Bao cao khong ton tai");
                } else if (bc.getDiemBaoCao() > 0) {
                    System.out.println("Bao cao da cham");
                } else if (!(bc instanceof BaoCaoKhoaLuan)) {
                    System.out.println("Bao cao khong phai la bao cao khoa luan");
                } else if (kiemTraBaoCaoCoHoiDong(bc)) {
                    System.out.println("Bao cao da co hoi dong cham");
                    bc = null;
                }

            } while (bc == null
                    || bc.getDiemBaoCao() > 0
                    || !(bc instanceof BaoCaoKhoaLuan));
            dsKhoaLuan.add((BaoCaoKhoaLuan) bc);
            tenGVHuongDan.add(bc.getTenGiangVienHuongDan());
        }

        GiangVien gv = null;
        gv = themGiaoVienVaoHoiDong("Chu tich", qlgv, tenGVHuongDan);
        dsGvThamGia.add(gv);
        qlnv.getDsNhiemVu().add(new NhiemVu("Chu tich", gv, this));

        gv = themGiaoVienVaoHoiDong("Thu ki", qlgv, tenGVHuongDan);
        dsGvThamGia.add(gv);
        qlnv.getDsNhiemVu().add(new NhiemVu("Thu ki", gv, this));

        gv = themGiaoVienVaoHoiDong("Phan bien", qlgv, tenGVHuongDan);
        dsGvThamGia.add(gv);
        qlnv.getDsNhiemVu().add(new NhiemVu("Phan bien", gv, this));
        int soLuongUyVien = -1;
        do {
            System.out.println("Nhap so luong uy vien can cham (0-2): ");
            soLuongUyVien = KtInput.getInputNumber();
        } while (soLuongUyVien < 0 || soLuongUyVien > 2);
        for (int i = 0; i < soLuongUyVien; i++) {
            gv = themGiaoVienVaoHoiDong("Uy vien", qlgv, tenGVHuongDan);
            dsGvThamGia.add(gv);
            qlnv.getDsNhiemVu().add(new NhiemVu("Uy vien", gv, this));
        }
        System.out.print("Nhap ngay lam viec: ");
        ngayHoiDongLamViec = KtInput.getInputDate();
        ArrayList<BaoCao> ds = qlbc.getDs();
        for (int i = 0; i < soLuongBaoCao; i++) {
            for (int j = 0; j < ds.size(); j++) {
                if (ds.get(j).getMaBaoCao().equals(dsKhoaLuan.get(i).getMaBaoCao())) {
                    ds.get(j).setNgayBaoCao(ngayHoiDongLamViec);
                    ((BaoCaoKhoaLuan) ds.get(j)).setDsThanhVienBaoVeKhoaLuan(dsGvThamGia);
                    break;
                }
            }
        }

        qlbc.setDs(ds);
        qlhd.getDs().add(this);
        System.out.println("Thanh lap hoi dong thanh cong");
    }

    public void chamDiem() {
        for (int i = 0; i < dsKhoaLuan.size(); i++) {
            ArrayList<Double> dsDiemTam = new ArrayList<>();
            System.out.println(dsKhoaLuan.get(i).tenBaoCao);
            System.out.println("Nhap diem: ");
            Double diemTb = 0.0;
            for (int j = 0; j < dsGvThamGia.size(); j++) {
                System.out.printf("%-20s:", dsGvThamGia.get(j).getHoTen());
                dsDiemTam.add(KtInput.getInputDiem());
                diemTb += dsDiemTam.get(j);
            }
            diemTb /= (dsDiemTam.size());
            dsKhoaLuan.get(i).setDiemBaoCao(diemTb);
            dsKhoaLuan.get(i).setDsDiem(dsDiemTam);
            System.out.print("Danh gia cua hoi dong: ");
            dsKhoaLuan.get(i).setDanhGiacuagv(KtInput.getInput());
        }
    }

    public void hienThi(QuanLyNhiemVu qlnv) {
        System.out.printf("ma hoi dong: %s\n", this.maHoiDong);
        System.out.println("Bao cao:");
        for (int i = 0; i < dsKhoaLuan.size(); i++) {
            System.out.println(dsKhoaLuan.get(i).tenBaoCao);
        }
        System.out.printf("Ngay lam viec: %s\n", new SimpleDateFormat("dd/MM/yyyy").format(ngayHoiDongLamViec));
        System.out.println("Thanh vien hoi dong:");
        for (int i = 0; i < dsGvThamGia.size(); i++) {
            System.out.printf("%-15s: %s\n", dsGvThamGia.get(i).getHoTen(),
                    qlnv.findNvByGvAndHd(dsGvThamGia.get(i).getMSGV(), maHoiDong).getVaiTro());
        }
    }

    // public HoiDongBaoVeKhoaLuan(ArrayList<String> dsKhoaLuan, String
    // ngayHoiDongLamViec, ArrayList<GiangVien> dsGvThamGia) throws ParseException{
    // this(dsKhoaLuan,f.parse(ngayHoiDongLamViec),dsGvThamGia);
    // }
    /**
     * @return the dsKhoaLuan
     */
    public ArrayList<BaoCaoKhoaLuan> getDsKhoaLuan() {
        return dsKhoaLuan;
    }

    /**
     * @param dsKhoaLuan the dsKhoaLuan to set
     */
    public void setDsKhoaLuan(ArrayList<BaoCaoKhoaLuan> dsKhoaLuan) {
        this.dsKhoaLuan = dsKhoaLuan;
    }

    /**
     * @return the ngayHoiDongLamViec
     */
    public Date getNgayHoiDongLamViec() {
        return ngayHoiDongLamViec;
    }

    /**
     * @param ngayHoiDongLamViec the ngayHoiDongLamViec to set
     */
    public void setNgayHoiDongLamViec(Date ngayHoiDongLamViec) {
        this.ngayHoiDongLamViec = ngayHoiDongLamViec;
    }

    /**
     * @return the dsGvThamGia
     */
    public ArrayList<GiangVien> getDsGvThamGia() {
        return dsGvThamGia;
    }

    /**
     * @param dsGvThamGia the dsGvThamGia to set
     */
    public void setDsGvThamGia(ArrayList<GiangVien> dsGvThamGia) {
        this.dsGvThamGia = dsGvThamGia;
    }

    /**
     * @return the maHoiDong
     */
    public String getMaHoiDong() {
        return maHoiDong;
    }

    /**
     * @param maHoiDong the maHoiDong to set
     */
    public void setMaHoiDong(String maHoiDong) {
        this.maHoiDong = maHoiDong;
    }
}
