package oop;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Them SV");
            System.out.println("2. In tat ca");
            System.out.println("3. In theo lop");
            System.out.println("4. In theo nganh");
            System.out.println("5. Sap xep theo diem");
            System.out.println("6. In theo thang sinh");
            System.out.println("0. Thoat");

            System.out.print("Chon: ");
            int chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1: QuanLiSinhVien.themSV(); break;
                case 2: QuanLiSinhVien.inTatCa(); break;
                case 3: QuanLiSinhVien.inTheoLop(); break;
                case 4: QuanLiSinhVien.inTheoNganh(); break;
                case 5: QuanLiSinhVien.sapXepTheoDiem(); break;
                case 6: QuanLiSinhVien.inTheoThang(); break;
                case 0: System.exit(0);
            }
        }
    }
}