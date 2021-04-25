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

    @NotNull
    @Column(name="gol1AC",nullable = false)
    private Integer gol1AC;

    @NotNull
    @Column(name="gol1D2A",nullable = false)
    private Integer gol1D2A;

    @NotNull
    @Column(name="gol2B",nullable = false)
    private Integer gol2B;

    @NotNull
    @Column(name="gol2CD",nullable = false)
    private Integer gol2CD;

    @NotNull
    @Column(name="gol3A",nullable = false)
    private Integer gol3A;

    @NotNull
    @Column(name="gol3BC",nullable = false)
    private Integer gol3BC;

    @NotNull
    @Column(name="gol3D4AB",nullable = false)
    private Integer gol3D4AB;

    @NotNull
    @Column(name="gol4CD",nullable = false)
    private Integer gol4CD;

    @NotNull
    @Column(name="gol5AD",nullable = false)
    private Integer gol5AD;


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

    public Integer getGol1AC() {
        return gol1AC;
    }

    public void setGol1AC(Integer gol1AC) {
        this.gol1AC = gol1AC;
    }

    public Integer getGol1D2A() {
        return gol1D2A;
    }

    public void setGol1D2A(Integer gol1D2A) {
        this.gol1D2A = gol1D2A;
    }

    public Integer getGol2B() {
        return gol2B;
    }

    public void setGol2B(Integer gol2B) {
        this.gol2B = gol2B;
    }

    public Integer getGol2CD() {
        return gol2CD;
    }

    public void setGol2CD(Integer gol2CD) {
        this.gol2CD = gol2CD;
    }

    public Integer getGol3A() {
        return gol3A;
    }

    public void setGol3A(Integer gol3A) {
        this.gol3A = gol3A;
    }

    public Integer getGol3BC() {
        return gol3BC;
    }

    public void setGol3BC(Integer gol3BC) {
        this.gol3BC = gol3BC;
    }

    public Integer getGol3D4AB() {
        return gol3D4AB;
    }

    public void setGol3D4AB(Integer gol3D4AB) {
        this.gol3D4AB = gol3D4AB;
    }

    public Integer getGol4CD() {
        return gol4CD;
    }

    public void setGol4CD(Integer gol4CD) {
        this.gol4CD = gol4CD;
    }

    public Integer getGol5AD() {
        return gol5AD;
    }

    public void setGol5AD(Integer gol5AD) {
        this.gol5AD = gol5AD;
    }

}
