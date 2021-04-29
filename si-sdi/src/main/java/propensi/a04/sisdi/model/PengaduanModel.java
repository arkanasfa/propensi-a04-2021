package propensi.a04.sisdi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="pengaduan")
public class PengaduanModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "no_karyawan", referencedColumnName = "noKaryawan", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private KaryawanModel no_karyawan;

    /* @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser_pengadu", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserModel idUser_pengadu; */



    @NotNull
    @Size(max=250)
    @Column(name="detailPengaduan",nullable = false)
    private String detailPengaduan;

    @NotNull
    @Column(name = "statusPengaduan", nullable = false)
    private Integer statusPengaduan;
    

/*     @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_status", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private StatusModel id_status; */

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="tanggalPengaduan",nullable = false)
    private LocalDate tanggalPengaduan;

    @NotNull
    @Column(name="kode_pengaduan",nullable = false)
    private String kode_pengaduan;

    

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
     * @return KaryawanModel return the no_karyawan
     */
    public KaryawanModel getNo_karyawan() {
        return no_karyawan;
    }

    /**
     * @param no_karyawan the no_karyawan to set
     */
    public void setNo_karyawan(KaryawanModel no_karyawan) {
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
     * @return Date return the tanggalPengaduan
     */
    public LocalDate getTanggalPengaduan() {
        return tanggalPengaduan;
    }

    /**
     * @param localDate the tanggalPengaduan to set
     */
    public void setTanggalPengaduan(LocalDate localDate) {
        this.tanggalPengaduan = localDate;
    }
    


/* 
    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    } */


    /**
     * @return String return the kode_pengaduan
     */
    public String getKode_pengaduan() {
        return kode_pengaduan;
    }

    /**
     * @param kode_pengaduan the kode_pengaduan to set
     */
    public void setKode_pengaduan(String kode_pengaduan) {
        this.kode_pengaduan = kode_pengaduan;
    }


    /**
     * @return Integer return the statusPengaduan
     */
    public Integer getStatusPengaduan() {
        return statusPengaduan;
    }

    /**
     * @param statusPengaduan the statusPengaduan to set
     */
    public void setStatusPengaduan(Integer statusPengaduan) {
        this.statusPengaduan = statusPengaduan;
    }

}
