package com.mycompany.baitaplonthu;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class App {

    private static QuanLyGiangVien qlgv = null;
    private static QuanLySinhVien qlsv = null;
    private static QuanLyBaoCao qlbc = null;
    private static QuanLyHoiDong qlhd = null;
    private static QuanLyNhiemVu qlnv = null;

    public static void init() throws ParseException {
        try {
            qlgv = new QuanLyGiangVien(FileIO.docQuanLyGiangVien());
            qlsv = new QuanLySinhVien(FileIO.docQuanLySinhVien());
            qlbc = new QuanLyBaoCao(FileIO.docQuanLyBaoCao(qlsv, qlgv));
            qlhd = new QuanLyHoiDong(FileIO.docQuanLyHoiDong(qlgv, qlbc));
            qlnv = new QuanLyNhiemVu(FileIO.docQuanLyNhiemVu(qlhd, qlgv));

        } catch (IOException ioException) {
            System.out.printf("Doc file that bai!.%n");
            int choice = -1;
            MenuContent.hienThiDocFileThatBai();
            while (choice != 1 && choice != 2) {
                choice = KtInput.getChoice();
            }
            switch (choice) {
                case 1 -> {
                    qlgv = new QuanLyGiangVien();
                    qlsv = new QuanLySinhVien();
                    qlbc = new QuanLyBaoCao();
                    qlhd = new QuanLyHoiDong();
                    qlnv = new QuanLyNhiemVu();
                }
                case 2 ->
                    System.exit(1);
            }
        }
    }

    public static void end() {
        try {
            FileIO.ghiQuanLySinhVien(qlsv);
            FileIO.ghiQuanLyGiangVien(qlgv);
            FileIO.ghiQuanLyHoiDong(qlhd);
            FileIO.ghiQuanLyBaoCao(qlbc, qlgv);
            FileIO.ghiQuanLyNhiemVu(qlnv);
            System.out.println("Ghi file thanh cong");
        } catch (IOException ioException) {
            System.out.println("Khi File that bai!");
        }
    }

    public static void main(String[] args) throws ParseException {
        init();
        int choice = -1;
        while (choice != 0) {
            // KtInput
            MenuContent.hienThiChucNang();
            choice = KtInput.getChoice();
            switch (choice) {
                case 1:
                    while (choice != 0) {
                        // KtInput
                        MenuContent.hienThiQuanLyBaoCao();
                        choice = KtInput.getChoice();
                        BaoCao bc = null;
                        String ma;
                        switch (choice) {
                            case 1:
                                // Them
                                System.out.println("1. Bao cao khoa luan");
                                System.out.println("2. Bao cao thu tap toc nghiep");
                                System.out.println("3. Bao cao do an nganh");
                                System.out.print("Nhap loai bao cao can them: ");
                                int loai = KtInput.getInputNumber();
                                switch (loai) {
                                    case 1:
                                        bc = new BaoCaoKhoaLuan();
                                        bc.input(qlsv, qlgv, qlbc);
                                        break;
                                    case 2:
                                        bc = new BaoCaoThucTapTotNghiep();
                                        bc.input(qlsv, qlgv, qlbc);
                                        break;
                                    case 3:
                                        bc = new BaoCaoDoAnNganh();
                                        bc.input(qlsv, qlgv, qlbc);
                                        break;
                                    default:
                                        System.out.println("Loai khong hop le!");
                                }
                                if (bc != null) {
                                    qlbc.themThongTin(bc);
                                }
                                break;
                            case 2:
                                // Xoa
                                System.out.println("Nhap ma bao cao can xoa: ");
                                ma = KtInput.getInput();
                                bc = qlbc.findBcById(ma);
                                if (bc != null) {
                                    qlbc.xoaThongTin(ma);
                                    System.out.println("Xoa thanh cong!");
                                } else {
                                    System.out.println("Khong co!");
                                }
                                break;
                            case 3:
                                // sua
                                System.out.print("Nhap ma bao cao can sua: ");
                                ma = KtInput.getInput();
                                bc = qlbc.findBcById(ma);
                                BaoCao bcTam = null;
                                if (bc instanceof BaoCaoKhoaLuan)
                                    bcTam = ((BaoCaoKhoaLuan) bc).newInstance((BaoCaoKhoaLuan) bc);
                                else if (bc instanceof BaoCaoDoAnNganh)
                                    bcTam = ((BaoCaoDoAnNganh) bc).newInstance((BaoCaoDoAnNganh) bc);
                                else if (bc instanceof BaoCaoThucTapTotNghiep)
                                    bcTam = ((BaoCaoThucTapTotNghiep) bc).newInstance((BaoCaoThucTapTotNghiep) bc);
                                if (bcTam != null) {
                                    bcTam.hienThi();
                                    if (bcTam.diemBaoCao == -1) {
                                        if (bcTam instanceof BaoCaoKhoaLuan
                                                && !((BaoCaoKhoaLuan) bcTam).getDsThanhVienBaoVeKhoaLuan().isEmpty())
                                            System.out.println("Khong the sua vi da lap hoi dong nhung chua cham!");
                                        else {
                                            MenuContent.suaBaoCao1();
                                            System.out.print("Thuoc tinh can sua: ");
                                            choice = KtInput.getChoice();
                                            bcTam.suaBaoCao(choice, qlgv, qlsv);
                                            bcTam.hienThi();
                                            MenuContent.xacNhanXua();
                                            choice = KtInput.getChoice();
                                            while (choice < 1 || choice > 2) {
                                                System.out.print("Nhap lai: ");
                                                choice = KtInput.getChoice();
                                            }
                                            if (choice == 1) {
                                                qlbc.suaThongTin(bcTam);
                                            }
                                        }
                                    } else {
                                        if (bcTam instanceof BaoCaoKhoaLuan)
                                            MenuContent.suaBaoCao2();
                                        else if (bcTam instanceof BaoCaoDoAnNganh)
                                            MenuContent.suaBaoCao3();
                                        else if (bcTam instanceof BaoCaoThucTapTotNghiep)
                                            MenuContent.suaBaoCao4();
                                        System.out.print("Thuoc tinh can sua: ");
                                        choice = KtInput.getChoice();
                                        bcTam.suaBaoCao(choice, qlgv, qlsv);
                                        bcTam.hienThi();
                                        MenuContent.xacNhanXua();
                                        choice = KtInput.getChoice();
                                        while (choice < 1 || choice > 2) {
                                            System.out.print("Nhap lai: ");
                                            choice = KtInput.getChoice();
                                        }
                                        if (choice == 1) {
                                            qlbc.suaThongTin(bcTam);
                                        }
                                    }
                                } else {
                                    System.out.println("Khong co!");
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    choice = -1;
                    break;
                case 2:
                    while (choice != 0) {
                        MenuContent.hienThiXemDSTheoTungLoai();
                        choice = KtInput.getChoice(); // KtInput
                        switch (choice) {
                            case 1:
                                // Xem bao cao loai BCKL
                                qlbc.hienThiDSTheoLoai(choice);
                                break;
                            case 2:
                                // Xem bao cao loai BCTN
                                qlbc.hienThiDSTheoLoai(choice);
                                break;
                            case 3:
                                // Xem bao cao loai BCDAN
                                qlbc.hienThiDSTheoLoai(choice);
                                break;
                            default:
                                break;
                        }
                    }
                    choice = -1;
                    break;
                case 3:
                    while (choice != 0) {
                        MenuContent.hienThiQuanLyDiemBaoCaoKhoaLuan();
                        // KtInput
                        choice = KtInput.getChoice();
                        switch (choice) {
                            case 1:
                                // Xem Diem BCKL
                                qlbc.xemDiemBaoCaoKhoaLuan();
                                break;
                            case 2:
                                String maBaoCaoKhoaLuan;
                                BaoCao bc;
                                System.out.print("Nhap ma bao cao can sua: ");
                                maBaoCaoKhoaLuan = KtInput.getInput();
                                bc = qlbc.findBcById(maBaoCaoKhoaLuan);
                                if (bc == null)
                                    System.out.println("Ma bao cao khong ton tai!");
                                if (!(bc instanceof BaoCaoKhoaLuan)) {
                                    System.out.println("Khong phai bao cao khoa luan!");
                                    bc = null;
                                } else if (!((BaoCaoKhoaLuan) bc).hasDiem()) {
                                    System.out.println("Bao cao chua cham diem!");
                                    bc = null;
                                }
                                if (bc != null) {
                                    ((BaoCaoKhoaLuan) bc).hienThiDSDiem();
                                    ((BaoCaoKhoaLuan) bc).suaDiemBaoCaoKhoaLuan();
                                }
                                // Sua Diem BCKL
                                break;
                            default:
                                break;
                        }
                    }
                    choice = -1;
                    break;
                case 4:
                    while (choice != 0) {
                        MenuContent.hienThiSapXepDSBaoCao();
                        // KtInput
                        choice = KtInput.getChoice();
                        switch (choice) {
                            case 1: // theo ngay
                                while (choice != 0) {
                                    qlbc.sapXepBaoCaoTheoNgay();
                                    MenuContent.hienThiSapXepTheoNgay();
                                    // KtInput
                                    choice = KtInput.getChoice();
                                    switch (choice) {
                                        case 1:
                                            // loc sap xep BCKL
                                            qlbc.hienThiDSTheoLoai(choice);
                                            break;
                                        case 2:
                                            // loc sap xep BCTN
                                            qlbc.hienThiDSTheoLoai(choice);
                                            break;
                                        case 3:
                                            // loc sap xep BCDAN
                                            qlbc.hienThiDSTheoLoai(choice);
                                            break;
                                        case 4:
                                            // loc sap xep ALL
                                            qlbc.xemThongTin();
                                            break;
                                        default:
                                            break;
                                    }
                                }
                                choice = -1;
                                break;
                            case 2: // theo ten
                                while (choice != 0) {
                                    qlbc.sapXepBaoCaoTheoTen();
                                    MenuContent.hienThiSapXepTheoTen();
                                    // KtInput
                                    choice = KtInput.getChoice();
                                    switch (choice) {
                                        case 1:
                                            // loc sap xep BCKL
                                            qlbc.hienThiDSTheoLoai(choice);
                                            break;
                                        case 2:
                                            // loc sap xep BCTN
                                            qlbc.hienThiDSTheoLoai(choice);
                                            break;
                                        case 3:
                                            // loc sap xep BCDAN
                                            qlbc.hienThiDSTheoLoai(choice);
                                            break;
                                        case 4:
                                            // loc sap xep ALL
                                            qlbc.xemThongTin();
                                            break;
                                    }
                                }
                                choice = -1;
                                break;
                            default:
                                break;
                        }
                    }
                    choice = -1;
                    break;
                case 5:
                    // Thanh lap hoi dong bao cao
                    HoiDongBaoVeKhoaLuan hd = new HoiDongBaoVeKhoaLuan();
                    hd.inputHoiDong(qlbc, qlgv, qlnv, qlhd);

                    break;
                case 6:
                    System.out.println("Nhap ngay hoi dong lam viec: ");
                    Date day = KtInput.getInputDate();
                    ArrayList<HoiDongBaoVeKhoaLuan> dsHD = null;
                    dsHD = qlhd.timKiemHoidongTheoNgayBaoVe(day);
                    // kiem tra hoi dong da cham chua, neu ma cham roi thi xoa khoi dsHD tam
                    for (int i = 0; i < dsHD.size(); i++) {
                        for (int j = 0; j < dsHD.get(i).getDsKhoaLuan().size(); j++) {
                            if (dsHD.get(i).getDsKhoaLuan().get(j).diemBaoCao != -1) {
                                dsHD.remove(i);
                                break;
                            }
                        }
                    }
                    if (!dsHD.isEmpty()) {
                        qlhd.hienThiDS(dsHD);
                        System.out.print("Chon hoi dong thu(1,2,3,...): ");
                        choice = KtInput.getChoice();
                        while (choice > dsHD.size() || choice <= 0) {
                            System.out.print("Nhap khong hop le!Nhap lai: ");
                            choice = KtInput.getChoice();
                        }
                        dsHD.get(choice - 1).chamDiem();
                    } else {
                        System.out.println("Khong co hoi dong lam viec vao ngay nay!");
                    }
                    break;
                case 7:
                    while (choice != 0) {
                        MenuContent.hienThiTimKiem();
                        // KtInput
                        choice = KtInput.getChoice();
                        switch (choice) {
                            case 1: // Tim kiem bao cao
                                while (choice != 0) {
                                    MenuContent.hienThiTimKiemBaoCao();
                                    // KtInput
                                    choice = KtInput.getChoice();
                                    switch (choice) {
                                        case 1:
                                            // Tim kiem bao cao Theo ten
                                            String ten;
                                            System.out.print("Nhap ten bao cao muon tim kiem:");
                                            ten = KtInput.getInput();

                                            qlbc.xemThongTin(qlbc.timKiemTheoTen(ten));
                                            break;
                                        case 2:
                                            // Tim kiem bao cao theo khoang ngay bao cao
                                            Date start;
                                            Date end;

                                            System.out.println("Nhap ngay bat dau");
                                            start = KtInput.getInputDate();
                                            System.out.println("Nhap ngay ket thuc");
                                            end = KtInput.getInputDate();

                                            qlbc.xemThongTin(qlbc.timKiemTheoNgay(start, end));
                                            break;
                                        default:
                                            break;
                                    }
                                }
                                choice = -1;
                                break;
                            case 2: // Tim kiem hoi dong
                                while (choice != 0) {
                                    MenuContent.hienThiTimKiemHoiDong();
                                    // KtInput
                                    choice = KtInput.getChoice();
                                    ArrayList<HoiDongBaoVeKhoaLuan> hdbvkl = null;
                                    switch (choice) {
                                        case 1:
                                            // Tim kiem hoi dong theo ngay bao ve
                                            Date ngay;

                                            System.out.println("Nhap ngay");
                                            ngay = KtInput.getInputDate();
                                            hdbvkl = qlhd.timKiemHoidongTheoNgayBaoVe(ngay);
                                            if (hdbvkl != null)
                                                qlhd.hienThiDS(hdbvkl, qlnv);
                                            else
                                                System.out.println("Khong tim thay hoi dong");
                                            break;
                                        case 2:
                                            // Tim kiem hoi dong theo khoang ngay bao ve
                                            Date start;
                                            Date end;
                                            System.out.print("Nhap ngay bat dau: ");
                                            start = KtInput.getInputDate();
                                            System.out.print("Nhap ngay ket thuc: ");
                                            end = KtInput.getInputDate();
                                            hdbvkl = qlhd.timKiemHoidongTheoNgayBaoVe(start, end);
                                            if (hdbvkl != null)
                                                qlhd.hienThiDS(hdbvkl, qlnv);
                                            else
                                                System.out.println("Khong tim thay hoi dong!");
                                            break;
                                    }
                                }
                                choice = -1;
                                break;
                            default:
                                break;
                        }
                    }
                    choice = -1;
                    break;
                default:
                    break;
            }
        }

        end();

    }
}
