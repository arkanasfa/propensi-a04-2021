package propensi.a04.sisdi.DTO;

public class PengaduanDTOModel {
    private Long no_karyawan;
    private String detailPengaduan;
    private Long id;
    private int skor;

    

    

    

    /**
     * @return Long return the no_karyawan
     */
    public Long getNo_karyawan() {
        return no_karyawan;
    }

    /**
     * @param no_karyawan the no_karyawan to set
     */
    public void setNo_karyawan(Long no_karyawan) {
        this.no_karyawan = no_karyawan;
    }

    /**
     * @return String return the detailPengaduan
     */
    public String getDetailPengaduan() {
        return detailPengaduan;
    }

    /**
     * @param detailPengaduan the detailPengaduan to set
     */
    public void setDetailPengaduan(String detailPengaduan) {
        this.detailPengaduan = detailPengaduan;
    }


    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * @return int return the skor
     */
    public int getSkor() {
        return skor;
    }

    /**
     * @param skor the skor to set
     */
    public void setSkor(int skor) {
        this.skor = skor;
    }

}
