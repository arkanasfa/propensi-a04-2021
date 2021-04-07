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

    @NotNull
    @Size(max=250)
    @Column(name="detailPengaduan",nullable = false)
    private String detailPengaduan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_status", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private StatusModel id_status;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="tanggalPengaduan",nullable = false)
    private Date tanggalPengaduan;

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

    public Date getTanggalPengaduan() {
        return tanggalPengaduan;
    }

    public void setTanggalPengaduan(Date tanggalPengaduan) {
        this.tanggalPengaduan = tanggalPengaduan;
    }

    public StatusModel getId_status() {
        return id_status;
    }

    public void setId_status(StatusModel id_status) {
        this.id_status = id_status;
    }
}
