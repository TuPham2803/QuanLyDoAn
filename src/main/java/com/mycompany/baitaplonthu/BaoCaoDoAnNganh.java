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
public class BaoCaoDoAnNganh extends BaoCaoCoTyLeDaoVan {

    public BaoCaoDoAnNganh(){
        super();
    }
    public BaoCaoDoAnNganh(String maBaoCao, String tenBaoCao, String chuoiLink, Date ngayBaoCao, ArrayList<SinhVien> dssv, String tenGiangVienHuongDan, double diemBaoCao, double tyLeKiemTraDaoVan) {
        super(maBaoCao, tenBaoCao, chuoiLink, ngayBaoCao, dssv, tenGiangVienHuongDan, diemBaoCao,tyLeKiemTraDaoVan);
    }
    public BaoCaoDoAnNganh newInstance(BaoCaoDoAnNganh bc) throws ParseException{
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return new BaoCaoDoAnNganh(bc.maBaoCao, bc.tenBaoCao, bc.chuoiLink, df.parse(df.format(bc.ngayBaoCao)),
                bc.dssv, bc.tenGiangVienHuongDan, bc.diemBaoCao, bc.getTyLeDaoVan());
    }

}
