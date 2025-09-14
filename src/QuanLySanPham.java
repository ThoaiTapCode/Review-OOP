import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class QuanLySanPham {
    private ArrayList<SanPham> dsSanPham;
    private Scanner scanner;
    
    public QuanLySanPham() {
        dsSanPham = new ArrayList<>();
        scanner = new Scanner(System.in);
        
        // Them du lieu mau
        themDuLieuMau();
    }
    
    private void themDuLieuMau() {
        dsSanPham.add(new SanPham("SP01", "Ao thun", "Quan ao", 150000, "Ao thun cotton", 30));
        dsSanPham.add(new SanPham("SP02", "Quan jean", "Quan ao", 350000, "Quan jean cao cap", 20));
        dsSanPham.add(new SanPham("SP03", "Dien thoai", "Dien tu", 5000000, "Dien thoai thong minh", 10));
        dsSanPham.add(new SanPham("SP04", "Laptop", "Dien tu", 15000000, "Laptop van phong", 5));
        dsSanPham.add(new SanPham("SP05", "Noi com dien", "Gia dung", 800000, "Noi com 1.5L", 15));
        
        // Ap dung giam gia mau
        dsSanPham.get(1).setPhanTramGiamGia(10); // Giam 10% cho san pham thu 2
        dsSanPham.get(3).setPhanTramGiamGia(15); // Giam 15% cho san pham thu 4
    }
    
    public void hienThiMenu() {
        int luaChon;
        do {
            System.out.println("\n===== QUAN LY SAN PHAM TRONG CUA HANG =====");
            System.out.println("1. Hien thi danh sach san pham");
            System.out.println("2. Cap nhat thong tin san pham");
            System.out.println("3. Hien thi san pham theo gia");
            System.out.println("4. Hien thi san pham theo danh muc");
            System.out.println("5. Tinh tong gia tri ton kho theo danh muc");
            System.out.println("6. Quan ly giam gia san pham");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon cua ban: ");
            
            luaChon = Integer.parseInt(scanner.nextLine());
            
            switch (luaChon) {
                case 1:
                    hienThiDanhSachSanPham();
                    break;
                case 2:
                    capNhatThongTinSanPham();
                    break;
                case 3:
                    hienThiSanPhamTheoGia();
                    break;
                case 4:
                    hienThiSanPhamTheoDanhMuc();
                    break;
                case 5:
                    tinhTongGiaTriTonKho();
                    break;
                case 6:
                    quanLyGiamGia();
                    break;
                case 0:
                    System.out.println("Cam on ban da su dung chuong trinh!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (luaChon != 0);
    }
    
    // 1. Hien thi danh sach san pham
    private void hienThiDanhSachSanPham() {
        System.out.println("\n===== DANH SACH SAN PHAM =====");
        inTieuDeBang();
        for (SanPham sp : dsSanPham) {
            System.out.println(sp);
        }
    }
    
    private void inTieuDeBang() {
        System.out.println("+-------+----------------------+------------+------------+---------+------------+------+");
        System.out.println("| Ma SP | Ten san pham         | Danh muc   | Gia goc    | Giam gia | Gia sau GG | Ton  |");
        System.out.println("+-------+----------------------+------------+------------+---------+------------+------+");
    }
    
    // 2. Cap nhat thong tin san pham
    private void capNhatThongTinSanPham() {
        System.out.println("\n===== CAP NHAT THONG TIN SAN PHAM =====");
        System.out.print("Nhap ma san pham can cap nhat: ");
        String maSP = scanner.nextLine();
        
        SanPham spCanCapNhat = null;
        for (SanPham sp : dsSanPham) {
            if (sp.getMaSP().equals(maSP)) {
                spCanCapNhat = sp;
                break;
            }
        }
        
        if (spCanCapNhat == null) {
            System.out.println("Khong tim thay san pham co ma " + maSP);
            return;
        }
        
        System.out.println("Thong tin hien tai:");
        inTieuDeBang();
        System.out.println(spCanCapNhat);
        
        System.out.println("\nChon thong tin can cap nhat:");
        System.out.println("1. Ten san pham");
        System.out.println("2. Danh muc");
        System.out.println("3. Gia");
        System.out.println("4. Mo ta");
        System.out.println("5. So luong ton kho");
        System.out.print("Nhap lua chon cua ban: ");
        
        int luaChon = Integer.parseInt(scanner.nextLine());
        switch (luaChon) {
            case 1:
                System.out.print("Nhap ten moi: ");
                spCanCapNhat.setTenSP(scanner.nextLine());
                break;
            case 2:
                System.out.print("Nhap danh muc moi: ");
                spCanCapNhat.setDanhMuc(scanner.nextLine());
                break;
            case 3:
                System.out.print("Nhap gia moi: ");
                spCanCapNhat.setGia(Double.parseDouble(scanner.nextLine()));
                break;
            case 4:
                System.out.print("Nhap mo ta moi: ");
                spCanCapNhat.setMoTa(scanner.nextLine());
                break;
            case 5:
                System.out.print("Nhap so luong ton kho moi: ");
                spCanCapNhat.setSoLuongTonKho(Integer.parseInt(scanner.nextLine()));
                break;
            default:
                System.out.println("Lua chon khong hop le!");
                return;
        }
        
        System.out.println("Cap nhat thanh cong!");
        System.out.println("Thong tin sau khi cap nhat:");
        inTieuDeBang();
        System.out.println(spCanCapNhat);
    }
    
    // 3. Hien thi san pham theo gia
    private void hienThiSanPhamTheoGia() {
        System.out.println("\n===== HIEN THI SAN PHAM THEO GIA =====");
        System.out.println("1. Sap xep gia tang dan");
        System.out.println("2. Sap xep gia giam dan");
        System.out.print("Nhap lua chon cua ban: ");
        
        int luaChon = Integer.parseInt(scanner.nextLine());
        List<SanPham> danhSachSapXep = new ArrayList<>(dsSanPham);
        
        if (luaChon == 1) {
            // Sap xep tang dan
            danhSachSapXep.sort(Comparator.comparing(SanPham::tinhGiaSauGiamGia));
            System.out.println("\nDanh sach san pham theo gia TANG DAN:");
        } else if (luaChon == 2) {
            // Sap xep giam dan
            danhSachSapXep.sort(Comparator.comparing(SanPham::tinhGiaSauGiamGia).reversed());
            System.out.println("\nDanh sach san pham theo gia GIAM DAN:");
        } else {
            System.out.println("Lua chon khong hop le!");
            return;
        }
        
        inTieuDeBang();
        for (SanPham sp : danhSachSapXep) {
            System.out.println(sp);
        }
    }
    
    // 4. Hien thi san pham theo danh muc
    private void hienThiSanPhamTheoDanhMuc() {
        System.out.println("\n===== HIEN THI SAN PHAM THEO DANH MUC =====");
        
        // Lay danh sach cac danh muc duy nhat
        List<String> danhMucList = dsSanPham.stream()
                                  .map(SanPham::getDanhMuc)
                                  .distinct()
                                  .collect(Collectors.toList());
        
        System.out.println("Cac danh muc hien co:");
        for (int i = 0; i < danhMucList.size(); i++) {
            System.out.println((i+1) + ". " + danhMucList.get(i));
        }
        
        System.out.print("Chon danh muc (nhap so): ");
        int luaChon = Integer.parseInt(scanner.nextLine());
        
        if (luaChon < 1 || luaChon > danhMucList.size()) {
            System.out.println("Lua chon khong hop le!");
            return;
        }
        
        String danhMucDaChon = danhMucList.get(luaChon-1);
        
        System.out.println("\nDanh sach san pham thuoc danh muc '" + danhMucDaChon + "':");
        inTieuDeBang();
        
        for (SanPham sp : dsSanPham) {
            if (sp.getDanhMuc().equals(danhMucDaChon)) {
                System.out.println(sp);
            }
        }
    }
    
    // 5. Tinh tong gia tri ton kho theo danh muc
    private void tinhTongGiaTriTonKho() {
        System.out.println("\n===== TONG GIA TRI TON KHO THEO DANH MUC =====");
        
        // Tao Map de luu tong gia tri theo danh muc
        Map<String, Double> tongGiaTriTheoDanhMuc = new HashMap<>();
        double tongGiaTriToanCuaHang = 0;
        
        // Tinh tong gia tri cho tung danh muc
        for (SanPham sp : dsSanPham) {
            String danhMuc = sp.getDanhMuc();
            double giaTriTonKho = sp.tinhGiaTriTonKho();
            
            // Cap nhat gia tri cho danh muc
            tongGiaTriTheoDanhMuc.put(danhMuc, 
                tongGiaTriTheoDanhMuc.getOrDefault(danhMuc, 0.0) + giaTriTonKho);
            
            // Cap nhat tong gia tri toan cua hang
            tongGiaTriToanCuaHang += giaTriTonKho;
        }
        
        // Hien thi ket qua
        System.out.println("+----------------------+-------------------------+");
        System.out.println("| Danh muc             | Tong gia tri ton kho    |");
        System.out.println("+----------------------+-------------------------+");
        
        for (Map.Entry<String, Double> entry : tongGiaTriTheoDanhMuc.entrySet()) {
            System.out.printf("| %-20s | %,19.0f |\n", entry.getKey(), entry.getValue());
        }
        
        System.out.println("+----------------------+-------------------------+");
        System.out.printf("| TONG TOAN CUA HANG   | %,19.0f |\n", tongGiaTriToanCuaHang);
        System.out.println("+----------------------+-------------------------+");
    }
    
    // 6. Quan ly giam gia san pham
    private void quanLyGiamGia() {
        System.out.println("\n===== QUAN LY GIAM GIA SAN PHAM =====");
        System.out.print("Nhap ma san pham can ap dung giam gia: ");
        String maSP = scanner.nextLine();
        
        SanPham spCanGiamGia = null;
        for (SanPham sp : dsSanPham) {
            if (sp.getMaSP().equals(maSP)) {
                spCanGiamGia = sp;
                break;
            }
        }
        
        if (spCanGiamGia == null) {
            System.out.println("Khong tim thay san pham co ma " + maSP);
            return;
        }
        
        System.out.println("Thong tin san pham:");
        inTieuDeBang();
        System.out.println(spCanGiamGia);
        
        System.out.print("Nhap phan tram giam gia (0-100): ");
        double phanTramGiamGia = Double.parseDouble(scanner.nextLine());
        spCanGiamGia.setPhanTramGiamGia(phanTramGiamGia);
        
        System.out.println("Ap dung giam gia thanh cong!");
        System.out.println("Thong tin sau khi giam gia:");
        inTieuDeBang();
        System.out.println(spCanGiamGia);
    }
    

}
