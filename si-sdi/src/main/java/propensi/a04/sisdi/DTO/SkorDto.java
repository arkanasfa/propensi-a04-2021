package propensi.a04.sisdi.DTO;

public class SkorDto {
    int skor;
    Long id;
    String nama;

    public String getNama() {
        return nama;
    }
    
      public void setNama(String nama) {
        this.nama = nama;
    }

    public Long getId() {
        return id;
    }
    
      public void setId(Long id) {
        this.id = id;
    }

    public int getSkor() {
        return skor;
    }
    
      public void setSkor(int skor) {
        this.skor = skor;
    }
    
}
