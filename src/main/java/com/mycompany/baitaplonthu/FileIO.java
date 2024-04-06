package com.mycompany.baitaplonthu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileIO {

    private static final String DULIEU_SINHVIEN_PATH = "./src/main/java/DuLieu/SinhVien";
    private static final String DULIEU_GIANGVIEN_PATH = "./src/main/java/DuLieu/GiangVien";
    private static final String DULIEU_BAOCAO_PATH = "./src/main/java/DuLieu/BaoCao";
    private static final String DULIEU_HOIDONG_PATH = "./src/main/java/DuLieu/HoiDong";
    private static final String DULIEU_NHIEMVU_PATH = "./src/main/java/DuLieu/NhiemVu";
    private static final String DULIEU_TEST = "./src/main/java/DuLieu/TestOutput";
    public static final SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
    private static File f = null;
    private static FileWriter fw = null;

    private FileIO() {
    }

    public static ArrayList<SinhVien> docQuanLySinhVien() throws FileNotFoundException {
        ArrayList<SinhVien> ds = new ArrayList<>();
        f = new File(DULIEU_SINHVIEN_PATH);
        try ( Scanner sc = new Scanner(f)) {
            while (sc.hasNext()) {
                String[] sv = sc.nextLine().split(",");
                ds.add(new SinhVien(sv[0], sv[1], sv[2], sv[3], sv[4], sv[5]));
            }
        }
        return ds;
    }

    public static ArrayList<GiangVien> docQuanLyGiangVien() throws FileNotFoundException {
        ArrayList<GiangVien> ds = new ArrayList<>();
        f = new File(DULIEU_GIANGVIEN_PATH);
        try ( Scanner sc = new Scanner(f)) {
            while (sc.hasNext()) {
                String[] gv = sc.nextLine().split(",");
                ds.add(new GiangVien(gv[0], gv[1], gv[2], gv[3]));
            }
        }
        return ds;
    }

    public static ArrayList<BaoCao> docQuanLyBaoCao(QuanLySinhVien qlsv, QuanLyGiangVien qlgv)
            throws FileNotFoundException, ParseException {
        f = new File(DULIEU_BAOCAO_PATH);
        ArrayList<BaoCao> ds = new ArrayList<>();
        try ( Scanner sc = new Scanner(f)) {
            while (sc.hasNext()) {
                String[] baoCao = sc.nextLine().split(",");
                String[] dsIDSinhVienThamGia = baoCao[5].split(";");
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                ArrayList<SinhVien> sinhVienThamGia = new ArrayList<>();

                for (String idSinhVien : dsIDSinhVienThamGia) {
                    sinhVienThamGia.add(qlsv.findSvById(idSinhVien));
                }

                switch (baoCao[0]) {
                    case "1" ->
                        ds.add(new BaoCaoDoAnNganh(baoCao[1], baoCao[2], baoCao[3], formatter.parse(baoCao[4]),
                                sinhVienThamGia,
                                qlgv.findGvById(baoCao[6]).getHoTen(), Double.parseDouble(baoCao[7]), Double.parseDouble(baoCao[8])));
                    case "2" -> {
                        ArrayList<GiangVien> dsThanhVienBaoVeKhoaLuan = new ArrayList<>();
                        ArrayList<Double> dsDiem = new ArrayList<>();
                        if (baoCao.length == 12) {
                            String[] dsGiangVien = baoCao[10].split(";");
                            String[] dsDiemString = baoCao[11].split(";");
                            for (String idGiangVien : dsGiangVien) {
                                dsThanhVienBaoVeKhoaLuan.add(qlgv.findGvById(idGiangVien));
                            }
                            for (String diem : dsDiemString) {
                                dsDiem.add(Double.valueOf(diem));
                            }
                        } else if (baoCao.length == 11) {
                            String[] dsGiangVien = baoCao[10].split(";");
                            for (String idGiangVien : dsGiangVien) {
                                dsThanhVienBaoVeKhoaLuan.add(qlgv.findGvById(idGiangVien));
                            }
                        }

                        ds.add(new BaoCaoKhoaLuan(baoCao[1], baoCao[2], baoCao[3], formatter.parse(baoCao[4]),
                                sinhVienThamGia,
                                qlgv.findGvById(baoCao[6]).getHoTen(), Double.parseDouble(baoCao[7]),
                                Double.parseDouble(baoCao[8]), baoCao[9],
                                dsThanhVienBaoVeKhoaLuan, dsDiem));
                    }
                    case "3" ->
                        ds.add(new BaoCaoThucTapTotNghiep(baoCao[1], baoCao[2], baoCao[3], formatter.parse(baoCao[4]),
                                sinhVienThamGia, qlgv.findGvById(baoCao[6]).getHoTen(), Double.parseDouble(baoCao[7]), baoCao[8]));

                }
            }
        }
        return ds;
    }

    public static ArrayList<HoiDongBaoVeKhoaLuan> docQuanLyHoiDong(QuanLyGiangVien qlgv, QuanLyBaoCao qlbc)
            throws FileNotFoundException, ParseException {
        f = new File(DULIEU_HOIDONG_PATH);
        ArrayList<HoiDongBaoVeKhoaLuan> ds = new ArrayList<>();
        try ( Scanner sc = new Scanner(f)) {
            while (sc.hasNext()) {
                String[] hd = sc.nextLine().split(",");
                String[] dsIDKhoaLuan = hd[1].split(";");

                ArrayList<BaoCaoKhoaLuan> dsKhoaLuan = new ArrayList<>();

                for (String idKhoaLuan : dsIDKhoaLuan) {
                    dsKhoaLuan.add((BaoCaoKhoaLuan) qlbc.findBcById(idKhoaLuan));
                }

                String[] dsIDGiangVien = hd[3].split(";");
                ArrayList<GiangVien> dsGvThamGia = new ArrayList<>();
                for (String idGiangVien : dsIDGiangVien) {
                    dsGvThamGia.add(qlgv.findGvById(idGiangVien));
                }
                ds.add(new HoiDongBaoVeKhoaLuan(hd[0], dsKhoaLuan, d.parse(hd[2]), dsGvThamGia));
            }
        }
        return ds;
    }

    public static ArrayList<NhiemVu> docQuanLyNhiemVu(QuanLyHoiDong qlhd, QuanLyGiangVien qlgv)
            throws FileNotFoundException {
        ArrayList<NhiemVu> ds = new ArrayList<>();
        f = new File(DULIEU_NHIEMVU_PATH);
        try ( Scanner sc = new Scanner(f)) {
            while (sc.hasNext()) {
                String[] nv = sc.nextLine().split(",");
                GiangVien giangVien = qlgv.findGvById(nv[1]);
                HoiDongBaoVeKhoaLuan hoiDong = qlhd.findHoiDongById(nv[2]);
                ds.add(new NhiemVu(nv[0], giangVien, hoiDong));
            }
        }

        return ds;
    }

    public static void ghiQuanLySinhVien(QuanLySinhVien qlsv) throws IOException {
        fw = new FileWriter(DULIEU_SINHVIEN_PATH);
        qlsv.getDs().forEach(sv -> {
            try {
                fw.write(
                        String.format("%s,%s,%s,%s,%s,%s%n", sv.getMSSV(), sv.getKhoaHoc(),
                                sv.getGioiTinh(),
                                sv.getNamSinh(), sv.getHoTen(), sv.getChuyenNganh())
                );
            } catch (IOException ioException) {
                System.out.println(ioException);
            }
        });
        fw.close();
    }

    public static void ghiQuanLyGiangVien(QuanLyGiangVien qlgv) throws IOException {
        fw = new FileWriter(DULIEU_GIANGVIEN_PATH);
        qlgv.getDs().forEach(gv -> {
            try {
                fw.write(String.format("%s,%s,%s,%s%n", gv.getMSGV(), gv.getHoTen(), gv.getHocHam(), gv.getHocVi()));
            } catch (IOException ioException) {
                System.out.println(ioException);
            }
        });
        fw.close();
    }

    public static void ghiQuanLyHoiDong(QuanLyHoiDong qlhd) throws IOException {
        fw = new FileWriter(DULIEU_HOIDONG_PATH);
        qlhd.getDs().forEach(hd -> {
            try {
                fw.write(hd.getMaHoiDong() + ",");
            } catch (IOException ex) {
                Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int i = 0; i < hd.getDsKhoaLuan().size() - 1; i++) {
                try {
                    fw.write(hd.getDsKhoaLuan().get(i).getMaBaoCao() + ";");
                } catch (IOException ex) {
                    Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                fw.write(hd.getDsKhoaLuan().get(hd.getDsKhoaLuan().size() - 1).getMaBaoCao() + ",");
            } catch (IOException ex) {
                Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                fw.write(d.format(hd.getNgayHoiDongLamViec()) + ",");
            } catch (IOException ex) {
                Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int i = 0; i < hd.getDsGvThamGia().size() - 1; i++) {
                try {
                    fw.write(hd.getDsGvThamGia().get(i).getMSGV() + ";");
                } catch (IOException ex) {
                    Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                fw.write(hd.getDsGvThamGia().get(hd.getDsGvThamGia().size() - 1).getMSGV() + "\n");
            } catch (IOException ex) {
                Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        fw.close();
    }

    public static void ghiQuanLyBaoCao(QuanLyBaoCao qlbc, QuanLyGiangVien qlgv) throws IOException {
        fw = new FileWriter(DULIEU_BAOCAO_PATH);
        qlbc.getDs().forEach(bc -> {
            // create list id SinhVien in String
            String gvhd = qlgv.findGvByName(bc.getTenGiangVienHuongDan()).getMSGV();
            String dsIDSinhVien = "";
            ArrayList<SinhVien> dsSinhVien = bc.getDssv();
            for (int i = 0; i < dsSinhVien.size() - 1; i++) {
                dsIDSinhVien += dsSinhVien.get(i).getMSSV() + ";";
            }
            dsIDSinhVien += dsSinhVien.get(dsSinhVien.size() - 1).getMSSV();
            String ngayBaoCao = d.format(bc.getNgayBaoCao());
            if (bc instanceof BaoCaoDoAnNganh) {
                try {
                    fw.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s\n", "1", bc.getMaBaoCao(), bc.getTenBaoCao(),
                            bc.getChuoiLink(),
                            ngayBaoCao, dsIDSinhVien, gvhd, bc.getDiemBaoCao(),
                            ((BaoCaoDoAnNganh) bc).getTyLeDaoVan()));
                } catch (IOException ioException) {
                    System.out.println(ioException);

                }
            } else if (bc instanceof BaoCaoKhoaLuan baoCaoKhoaLuan) {
                String dsIdGiangVienThamGia = "";
                if (!baoCaoKhoaLuan.getDsThanhVienBaoVeKhoaLuan().isEmpty()) {
                    ArrayList<GiangVien> dsGiangVien = baoCaoKhoaLuan.getDsThanhVienBaoVeKhoaLuan();
                    for (int i = 0; i < dsGiangVien.size() - 1; i++) {
                        dsIdGiangVienThamGia += dsGiangVien.get(i).getMSGV() + ";";
                    }
                    dsIdGiangVienThamGia += dsGiangVien.get(dsGiangVien.size() - 1).getMSGV();
                }
                String dsDiemS = "";
                if (!baoCaoKhoaLuan.getDsDiem().isEmpty()) {
                    ArrayList<Double> dsDiem = baoCaoKhoaLuan.getDsDiem();
                    for (int i = 0; i < dsDiem.size() - 1; i++) {
                        dsDiemS += dsDiem.get(i) + ";";
                    }
                    dsDiemS += dsDiem.get(dsDiem.size() - 1);
                }

                try {
                    fw.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n", "2", bc.getMaBaoCao(),
                            bc.getTenBaoCao(),
                            bc.getChuoiLink(),
                            ngayBaoCao, dsIDSinhVien, gvhd, bc.getDiemBaoCao(),
                            baoCaoKhoaLuan.getTyLeDaoVan(), baoCaoKhoaLuan.getDanhGiacuagv(),
                            dsIdGiangVienThamGia, dsDiemS));
                } catch (IOException ioException) {
                    System.out.println(ioException);
                }
            } else if (bc instanceof BaoCaoThucTapTotNghiep) {
                try {
                    fw.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s\n", "3", bc.getMaBaoCao(), bc.getTenBaoCao(),
                            bc.getChuoiLink(),
                            ngayBaoCao, dsIDSinhVien, gvhd, bc.getDiemBaoCao(),
                            ((BaoCaoThucTapTotNghiep) bc).getDanhgiaDoanhNghiep()));
                } catch (IOException ioException) {
                    System.out.println(ioException);
                }
            }
        });
        fw.close();
    }

    public static void ghiQuanLyNhiemVu(QuanLyNhiemVu qlnv) throws IOException {
        fw = new FileWriter(DULIEU_NHIEMVU_PATH);
        qlnv.getDsNhiemVu().forEach(nv -> {
            try {
                fw.write(
                        String.format("%s,%s,%s\n", nv.getVaiTro(), nv.getGiangVien().getMSGV(), nv.getHoiDong().getMaHoiDong())
                );
            } catch (IOException ioException) {
                System.out.println(ioException);
            }
        });
        fw.close();
    }
}
