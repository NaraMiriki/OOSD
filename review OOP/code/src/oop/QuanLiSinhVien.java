package oop;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class QuanLiSinhVien {

    private static Scanner sc = new Scanner(System.in);

    // Chuẩn hóa họ tên
    public static String chuanHoaTen(String ten) {
        ten = ten.trim().toLowerCase();
        String[] words = ten.split("\\s+");
        String result = "";
        for (int i = 0; i < words.length; i++) {
            result += words[i].substring(0,1).toUpperCase()
                    + words[i].substring(1) + " ";
        }
        return result.trim();
    }

    // Validate mã sinh viên
    public static boolean checkMaSV(String masv, String nganh) {
        if (masv.length() != 10) return false;

        if (nganh.equals("CNTT") && masv.startsWith("455105"))
            return true;

        if (nganh.equals("KTPM") && masv.startsWith("455109"))
            return true;

        return false;
    }

    // Validate ngày sinh + tuổi
    public static boolean checkTuoi(LocalDate ngaySinh) {
        int tuoi = Period.between(ngaySinh, LocalDate.now()).getYears();
        return tuoi >= 15 && tuoi <= 110;
    }

    // Thêm sinh viên
    public static void themSV() {
        try (Connection conn = DBConnection.getConnection()) {

            System.out.print("Ma SV: ");
            String masv = sc.nextLine();

            System.out.print("Ho ten: ");
            String hoten = chuanHoaTen(sc.nextLine());

            System.out.print("Ngay sinh (yyyy-mm-dd): ");
            LocalDate ngaySinh = LocalDate.parse(sc.nextLine());

            if (!checkTuoi(ngaySinh)) {
                System.out.println("Tuoi khong hop le!");
                return;
            }

            System.out.print("Nganh (CNTT/KTPM): ");
            String nganh = sc.nextLine();

            if (!nganh.equals("CNTT") && !nganh.equals("KTPM")) {
                System.out.println("Nganh khong hop le!");
                return;
            }

            if (!checkMaSV(masv, nganh)) {
                System.out.println("Ma SV khong dung dinh dang!");
                return;
            }

            System.out.print("Diem TB: ");
            double diem = Double.parseDouble(sc.nextLine());

            if (diem < 0.0 || diem > 10.0) {
                System.out.println("Diem khong hop le!");
                return;
            }

            System.out.print("Lop sinh hoat: ");
            String lop = sc.nextLine();

            String sql = "INSERT INTO sinhvien VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, masv);
            ps.setString(2, hoten);
            ps.setDate(3, Date.valueOf(ngaySinh));
            ps.setString(4, nganh);
            ps.setDouble(5, diem);
            ps.setString(6, lop);

            ps.executeUpdate();
            System.out.println("Them thanh cong!");

        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }
    }

    // In tất cả
    public static void inTatCa() {
        try (Connection conn = DBConnection.getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM sinhvien");

            while (rs.next()) {
                System.out.println(
                        rs.getString("masv") + " | " +
                        rs.getString("hoten") + " | " +
                        rs.getDate("ngaysinh") + " | " +
                        rs.getString("nganh") + " | " +
                        rs.getDouble("diemtb") + " | " +
                        rs.getString("lopsinhhoat")
                );
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // In theo lớp
    public static void inTheoLop() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("Nhap lop: ");
            String lop = sc.nextLine();

            String sql = "SELECT * FROM sinhvien WHERE lopsinhhoat=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, lop);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("masv") + " | " + rs.getString("hoten"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // In theo ngành
    public static void inTheoNganh() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("Nhap nganh: ");
            String nganh = sc.nextLine();

            String sql = "SELECT * FROM sinhvien WHERE nganh=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nganh);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("masv") + " | " + rs.getString("hoten"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Sắp xếp theo điểm
    public static void sapXepTheoDiem() {
        try (Connection conn = DBConnection.getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT * FROM sinhvien ORDER BY diemtb DESC");

            while (rs.next()) {
                System.out.println(rs.getString("masv") + " | " +
                        rs.getString("hoten") + " | " +
                        rs.getDouble("diemtb"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Sinh viên theo tháng sinh
    public static void inTheoThang() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("Nhap thang (1-12): ");
            int thang = Integer.parseInt(sc.nextLine());

            String sql = "SELECT * FROM sinhvien WHERE MONTH(ngaysinh)=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, thang);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("masv") + " | " + rs.getString("hoten"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
