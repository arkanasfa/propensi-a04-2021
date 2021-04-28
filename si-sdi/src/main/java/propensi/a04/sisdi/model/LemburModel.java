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
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="lembur")
public class LemburModel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_karyawan", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private KaryawanModel id_karyawan;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_infal", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private KaryawanModel id_infal;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="tanggalRequest",nullable = false)
    private Date tanggalRequest;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="tanggalLembur",nullable = false)
    private Date tanggalLembur;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    @Column(name="jamMulai",nullable = false)
    private Date jamMulai;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    @Column(name="jamSelesai",nullable = false)
    private Date jamSelesai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_status", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private StatusModel id_status;

    @NotNull
    @Column(name="alasan",nullable = false)
    private String alasan;

    @NotNull
    @Column(name="kode_lembur",nullable = false)
    private String kode_lembur;

    @NotNull
    @Column(name="jenis",nullable = false)
    private Integer jenis;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public KaryawanModel getId_karyawan() {
        return id_karyawan;
    }

    public void setId_karyawan(KaryawanModel id_karyawan) {
        this.id_karyawan = id_karyawan;
    }

    public KaryawanModel getId_infal() {
        return id_infal;
    }

    public void setId_infal(KaryawanModel id_infal) {
        this.id_infal = id_infal;
    }

    public Date getTanggalRequest() {
        return tanggalRequest;
    }

    public void setTanggalRequest(Date tanggalRequest) {
        this.tanggalRequest = tanggalRequest;
    }

    public Date getTanggalLembur() {
        return tanggalLembur;
    }

    public void setTanggalLembur(Date tanggalLembur) {
        this.tanggalLembur = tanggalLembur;
    }

    public Date getJamMulai() {
        return jamMulai;
    }

    public void setJamMulai(Date jamMulai) {
        this.jamMulai = jamMulai;
    }

    public Date getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(Date jamSelesai) {
        this.jamSelesai = jamSelesai;
    }

    public StatusModel getId_status() {
        return id_status;
    }

    public void setId_status(StatusModel id_status) {
        this.id_status = id_status;
    }

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }

    public String getKode_lembur() {
        return kode_lembur;
    }

    public void setKode_lembur(String kode_lembur) {
        this.kode_lembur = kode_lembur;
    }

    public Integer getJenis() {
        return jenis;
    }

    public void setJenis(Integer jenis) {
        this.jenis = jenis;
    }
}
