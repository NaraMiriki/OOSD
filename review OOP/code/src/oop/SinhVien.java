package oop;

import java.time.LocalDate;

public class SinhVien {
    private String maSV;
    private String hoTen;
    private LocalDate ngaySinh;
    private String nganh;
    private double diemTB;
    private String lopSinhHoat;

    public SinhVien(String maSV, String hoTen, LocalDate ngaySinh,
                    String nganh, double diemTB, String lopSinhHoat) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.nganh = nganh;
        this.diemTB = diemTB;
        this.lopSinhHoat = lopSinhHoat;
    }

    public String getMaSV() { return maSV; }
    public String getHoTen() { return hoTen; }
    public LocalDate getNgaySinh() { return ngaySinh; }
    public String getNganh() { return nganh; }
    public double getDiemTB() { return diemTB; }
    public String getLopSinhHoat() { return lopSinhHoat; }
}