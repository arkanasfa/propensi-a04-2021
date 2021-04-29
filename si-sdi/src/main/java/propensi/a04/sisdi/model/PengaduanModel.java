package propensi.a04.sisdi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;


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

    @NotNull
    @Size(max=250)
    @Column(name="detailPengaduan",nullable = false)
    private String detailPengaduan;
    

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="tanggalPengaduan",nullable = false)
    private LocalDate tanggalPengaduan;

    @NotNull
    @Column(name="kode_pengaduan",nullable = false)
    private String kode_pengaduan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_status", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private StatusModel id_status;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public KaryawanModel getNo_karyawan() {
        return no_karyawan;
    }

    public void setNo_karyawan(KaryawanModel no_karyawan) {
        this.no_karyawan = no_karyawan;
    }


    public String getDetailPengaduan() {
        return detailPengaduan;
    }


    public void setDetailPengaduan(String detailPengaduan) {
        this.detailPengaduan = detailPengaduan;
    }

    public LocalDate getTanggalPengaduan() {
        return tanggalPengaduan;
    }


    public void setTanggalPengaduan(LocalDate localDate) {
        this.tanggalPengaduan = localDate;
    }
    


    public String getKode_pengaduan() {
        return kode_pengaduan;
    }

    public void setKode_pengaduan(String kode_pengaduan) {
        this.kode_pengaduan = kode_pengaduan;
    }

    public StatusModel getId_status() {
        return id_status;
    }

    public void setId_status(StatusModel id_status) {
        this.id_status = id_status;
    }

}
