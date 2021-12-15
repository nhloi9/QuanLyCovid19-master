package nhom13.covid.Model;

import java.sql.Date;

/**
 * @author trdat
 */
public class CachLy {
    String hoVaTen;
    Integer maNhanKhau;
    Integer mucDo;
    boolean tinhTrang;
    Date tdCachLy;
    String kvCachLy;

    public CachLy(String hoVaTen, Integer maNhanKhau, Integer mucDo, boolean tinhTrang, Date tdCachLy, String kvCachLy) {
        this.hoVaTen = hoVaTen;
        this.maNhanKhau = maNhanKhau;
        this.mucDo = mucDo;
        this.tinhTrang = tinhTrang;
        this.tdCachLy = tdCachLy;
        this.kvCachLy = kvCachLy;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public Integer getMaNhanKhau() {
        return maNhanKhau;
    }

    public void setMaNhanKhau(Integer maNhanKhau) {
        this.maNhanKhau = maNhanKhau;
    }

    public Integer getMucDo() {
        return mucDo;
    }

    public void setMucDo(Integer mucDo) {
        this.mucDo = mucDo;
    }

    public boolean getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public Date getTdCachLy() {
        return tdCachLy;
    }

    public void setTdCachLy(Date tdCachLy) {
        this.tdCachLy = tdCachLy;
    }

    public String getKvCachLy() {
        return kvCachLy;
    }

    public void setKvCachLy(String kvCachLy) {
        this.kvCachLy = kvCachLy;
    }
}
