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
@Table(name="komponenPengaliGaji")
public class KomponenPengaliModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="lembur",nullable = false)
    private Long lembur;

    @NotNull
    @Column(name="infal",nullable = false)
    private Integer infal;

    @NotNull
    @Column(name="keluarga",nullable = false)
    private Integer keluarga;

    @NotNull
    @Column(name="PPH",nullable = false)
    private Float PPH;

    @NotNull
    @Column(name="uangSnack",nullable = false)
    private Integer uangSnack;

    @NotNull
    @Column(name="absensi",nullable = false)
    private Integer absensi;

    @NotNull
    @Column(name="tPPH",nullable = false)
    private Float tPPH;

    @NotNull
    @Column(name="tunjanganPrestasi",nullable = false)
    private Integer tunjanganPrestasi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLembur() {
        return lembur;
    }

    public void setLembur(Long lembur) {
        this.lembur = lembur;
    }

    public Integer getInfal() {
        return infal;
    }

    public void setInfal(Integer infal) {
        this.infal = infal;
    }

    public Integer getKeluarga() {
        return keluarga;
    }

    public void setKeluarga(Integer keluarga) {
        this.keluarga = keluarga;
    }

    public Float getPPH() {
        return PPH;
    }

    public void setPPH(Float PPH) {
        this.PPH = PPH;
    }

    public Integer getUangSnack() {
        return uangSnack;
    }

    public void setUangSnack(Integer uangSnack) {
        this.uangSnack = uangSnack;
    }

    public Integer getAbsensi() {
        return absensi;
    }

    public void setAbsensi(Integer absensi) {
        this.absensi = absensi;
    }

    public Float gettPPH() {
        return tPPH;
    }

    public void settPPH(Float tPPH) {
        this.tPPH = tPPH;
    }

    public Integer getTunjanganPrestasi() {
        return tunjanganPrestasi;
    }

    public void setTunjanganPrestasi(Integer tunjanganPrestasi) {
        this.tunjanganPrestasi = tunjanganPrestasi;
    }
}
