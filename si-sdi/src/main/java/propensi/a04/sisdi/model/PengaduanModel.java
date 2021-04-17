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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idOrtu_pengadu", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private OrangTuaModel idOrtu_pengadu;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idSiswa_pengadu", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SiswaModel idSiswa_pengadu;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idKarya_pengadu", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private KaryawanModel idKarya_pengadu;

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
     * @return OrangTuaModel return the idOrtu_pengadu
     */
    public OrangTuaModel getIdOrtu_pengadu() {
        return idOrtu_pengadu;
    }

    /**
     * @param idOrtu_pengadu the idOrtu_pengadu to set
     */
    public void setIdOrtu_pengadu(OrangTuaModel idOrtu_pengadu) {
        this.idOrtu_pengadu = idOrtu_pengadu;
    }

    /**
     * @return SiswaModel return the idSiswa_pengadu
     */
    public SiswaModel getIdSiswa_pengadu() {
        return idSiswa_pengadu;
    }

    /**
     * @param idSiswa_pengadu the idSiswa_pengadu to set
     */
    public void setIdSiswa_pengadu(SiswaModel idSiswa_pengadu) {
        this.idSiswa_pengadu = idSiswa_pengadu;
    }

    /**
     * @return KaryawanModel return the idKarya_pengadu
     */
    public KaryawanModel getIdKarya_pengadu() {
        return idKarya_pengadu;
    }

    /**
     * @param idKarya_pengadu the idKarya_pengadu to set
     */
    public void setIdKarya_pengadu(KaryawanModel idKarya_pengadu) {
        this.idKarya_pengadu = idKarya_pengadu;
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
     * @return StatusModel return the id_status
     */
    public StatusModel getId_status() {
        return id_status;
    }

    /**
     * @param id_status the id_status to set
     */
    public void setId_status(StatusModel id_status) {
        this.id_status = id_status;
    }

    /**
     * @return Date return the tanggalPengaduan
     */
    public Date getTanggalPengaduan() {
        return tanggalPengaduan;
    }

    /**
     * @param tanggalPengaduan the tanggalPengaduan to set
     */
    public void setTanggalPengaduan(Date tanggalPengaduan) {
        this.tanggalPengaduan = tanggalPengaduan;
    }

}
