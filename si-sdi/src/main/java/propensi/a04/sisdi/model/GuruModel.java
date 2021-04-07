package propensi.a04.sisdi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="guru")
public class GuruModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="listSkorEksternal",nullable = false)
    private Integer listSkorEksternal;

    @NotNull
    @Column(name="statusGuru",nullable = false)
    private Integer statusGuru;

    @NotNull
    @Column(name="totalSkor",nullable = false)
    private Integer totalSkor;

    @NotNull
    @Column(name="boolSertifikasi",nullable = false)
    private Boolean boolSertifikasi;

    @OneToOne(mappedBy = "id_gurupengaju")
    private PasanganModel guruPengaju;

    @OneToOne(mappedBy = "id_guruprioritas1")
    private PasanganModel guruPrioritas1;

    @OneToOne(mappedBy = "id_guruprioritas2")
    private PasanganModel guruPrioritas2;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_karyawan", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private KaryawanModel id_karyawan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sertifikasi", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private GuruSertifikasiModel id_sertifikasi;

    @OneToMany(mappedBy = "id_guru", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<JawabanBorangModel> penilaianGuru;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getListSkorEksternal() {
        return listSkorEksternal;
    }

    public void setListSkorEksternal(Integer listSkorEksternal) {
        this.listSkorEksternal = listSkorEksternal;
    }

    public Integer getStatusGuru() {
        return statusGuru;
    }

    public void setStatusGuru(Integer statusGuru) {
        this.statusGuru = statusGuru;
    }

    public Integer getTotalSkor() {
        return totalSkor;
    }

    public void setTotalSkor(Integer totalSkor) {
        this.totalSkor = totalSkor;
    }

    public Boolean getBoolSertifikasi() {
        return boolSertifikasi;
    }

    public void setBoolSertifikasi(Boolean boolSertifikasi) {
        this.boolSertifikasi = boolSertifikasi;
    }

    public PasanganModel getGuruPengaju() {
        return guruPengaju;
    }

    public void setGuruPengaju(PasanganModel guruPengaju) {
        this.guruPengaju = guruPengaju;
    }

    public PasanganModel getGuruPrioritas1() {
        return guruPrioritas1;
    }

    public void setGuruPrioritas1(PasanganModel guruPrioritas1) {
        this.guruPrioritas1 = guruPrioritas1;
    }

    public PasanganModel getGuruPrioritas2() {
        return guruPrioritas2;
    }

    public void setGuruPrioritas2(PasanganModel getGuruPrioritas2) {
        this.guruPrioritas2 = getGuruPrioritas2;
    }

    public KaryawanModel getId_karyawan() {
        return id_karyawan;
    }

    public void setId_karyawan(KaryawanModel id_karyawan) {
        this.id_karyawan = id_karyawan;
    }

    public GuruSertifikasiModel getId_sertifikasi() {
        return id_sertifikasi;
    }

    public void setId_sertifikasi(GuruSertifikasiModel id_sertifikasi) {
        this.id_sertifikasi = id_sertifikasi;
    }

    public List<JawabanBorangModel> getPenilaianGuru() {
        return penilaianGuru;
    }

    public void setPenilaianGuru(List<JawabanBorangModel> penilaianGuru) {
        this.penilaianGuru = penilaianGuru;
    }
}
