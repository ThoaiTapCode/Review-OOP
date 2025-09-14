public class SanPham {
    // Thuoc tinh cua san pham
    private String maSP;
    private String tenSP;
    private String danhMuc;
    private double gia;
    private String moTa;
    private int soLuongTonKho;
    private double phanTramGiamGia;
    
    // Constructor
    public SanPham(String maSP, String tenSP, String danhMuc, double gia, String moTa, int soLuongTonKho) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.danhMuc = danhMuc;
        this.gia = gia;
        this.moTa = moTa;
        this.soLuongTonKho = soLuongTonKho;
        this.phanTramGiamGia = 0; // Mac dinh khong giam gia
    }
    
    // Getters va Setters
    public String getMaSP() {
        return maSP;
    }
    
    public String getTenSP() {
        return tenSP;
    }
    
    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }
    
    public String getDanhMuc() {
        return danhMuc;
    }
    
    public void setDanhMuc(String danhMuc) {
        this.danhMuc = danhMuc;
    }
    
    public double getGia() {
        return gia;
    }
    
    public void setGia(double gia) {
        this.gia = gia;
    }
    
    public String getMoTa() {
        return moTa;
    }
    
    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    
    public int getSoLuongTonKho() {
        return soLuongTonKho;
    }
    
    public void setSoLuongTonKho(int soLuongTonKho) {
        this.soLuongTonKho = soLuongTonKho;
    }
    
    public double getPhanTramGiamGia() {
        return phanTramGiamGia;
    }
    
    public void setPhanTramGiamGia(double phanTramGiamGia) {
        if (phanTramGiamGia >= 0 && phanTramGiamGia <= 100) {
            this.phanTramGiamGia = phanTramGiamGia;
        } else {
            System.out.println("Phan tram giam gia phai tu 0 den 100!");
        }
    }
    
    // Tinh gia sau khi giam gia
    public double tinhGiaSauGiamGia() {
        return gia * (1 - phanTramGiamGia / 100);
    }
    
    // Tinh gia tri ton kho
    public double tinhGiaTriTonKho() {
        return soLuongTonKho * tinhGiaSauGiamGia();
    }
    
    // Hien thi thong tin san pham
    @Override
    public String toString() {
        return String.format("| %-5s | %-20s | %-10s | %,10.0f | %5.1f%% | %,10.0f | %4d |", 
                maSP, tenSP, danhMuc, gia, phanTramGiamGia, 
                tinhGiaSauGiamGia(), soLuongTonKho);
    }
}
