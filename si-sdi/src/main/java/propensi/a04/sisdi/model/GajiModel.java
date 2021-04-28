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
@Table(name="gaji")
public class GajiModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="gajiPokok",nullable = false)
    private Integer gajiPokok;

    @NotNull
    @Column(name="tunjanganJabatan",nullable = false)
    private Integer tunjanganJabatan;

    @NotNull
    @Column(name="tunjanganFungsional",nullable = false)
    private Integer tunjanganFungsional;

    @NotNull
    @Column(name="tunjanganTransport",nullable = false)
    private Integer tunjanganTransport;

    @NotNull
    @Column(name="tunjangankeluarga",nullable = false)
    private Integer tunjanganKeluarga;

    @NotNull
    @Column(name="tunjanganKesehatan",nullable = false)
    private Integer tunjanganKesehatan;

    @NotNull
    @Column(name="tunjanganPerintis",nullable = false)
    private Integer tunjanganPerintis;

    @NotNull
    @Column(name="tunjanganPrestasi",nullable = false)
    private Integer tunjanganPrestasi;

    @NotNull
    @Column(name="tPPH",nullable = false)
    private Float tPPH;

    @NotNull
    @Column(name="uangSnack",nullable = false)
    private Integer uangSnack;

    @NotNull
    @Column(name="totalLembur",nullable = false)
    private Integer totalLembur;

    @NotNull
    @Column(name="totalInfal",nullable = false)
    private Integer totalInfal;

    @NotNull
    @Column(name="angsuranKopeg",nullable = false)
    private Integer angsuranKopeg;

    @NotNull
    @Column(name="angsuranBank",nullable = false)
    private Integer angsuranBank;

    @NotNull
    @Column(name="angsuranYayasan",nullable = false)
    private Integer angsuranYayasan;

    @NotNull
    @Column(name="potonganAbsensi",nullable = false)
    private Integer potonganAbsensi;

    @NotNull
    @Column(name="potonganPPH",nullable = false)
    private Integer potonganPPH;

    @NotNull
    @Column(name="THPB",nullable = false)
    private Integer THPB;

    @NotNull
    @Column(name="THPA",nullable = false)
    private Integer THPA;

    @NotNull
    @Column(name="totalFixCost",nullable = false)
    private Integer totalFixCost;

    @NotNull
    @Column(name="totalVarCost",nullable = false)
    private Integer totalVarCost;

    @NotNull
    @Column(name="totalPotongan",nullable = false)
    private Integer totalPotongan;

    @NotNull
    @Column(name="unit",nullable = false)
    private String unit;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_karyawan", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private KaryawanModel id_karyawan;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "dokumen", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private DokumenTotalModel dokumen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGajiPokok() {
        return gajiPokok;
    }

    public void setGajiPokok(Integer gajiPokok) {
        this.gajiPokok = gajiPokok;
    }

    public Integer getTunjanganJabatan() {
        return tunjanganJabatan;
    }

    public void setTunjanganJabatan(Integer tunjanganJabatan) {
        this.tunjanganJabatan = tunjanganJabatan;
    }

    public Integer getTunjanganFungsional() {
        return tunjanganFungsional;
    }

    public void setTunjanganFungsional(Integer tunjanganFungsional) {
        this.tunjanganFungsional = tunjanganFungsional;
    }

    public Integer getTunjanganTransport() {
        return tunjanganTransport;
    }

    public void setTunjanganTransport(Integer tunjanganTransport) {
        this.tunjanganTransport = tunjanganTransport;
    }

    public Integer getTunjanganKeluarga() {
        return tunjanganKeluarga;
    }

    public void setTunjanganKeluarga(Integer tunjanganKeluarga) {
        this.tunjanganKeluarga = tunjanganKeluarga;
    }

    public Integer getTunjanganKesehatan() {
        return tunjanganKesehatan;
    }

    public void setTunjanganKesehatan(Integer tunjanganKesehatan) {
        this.tunjanganKesehatan = tunjanganKesehatan;
    }

    public Integer getTunjanganPerintis() {
        return tunjanganPerintis;
    }

    public void setTunjanganPerintis(Integer tunjanganPerintis) {
        this.tunjanganPerintis = tunjanganPerintis;
    }

    public Integer getTunjanganPrestasi() {
        return tunjanganPrestasi;
    }

    public void setTunjanganPrestasi(Integer tunjanganPrestasi) {
        this.tunjanganPrestasi = tunjanganPrestasi;
    }

    public Float gettPPH() {
        return tPPH;
    }

    public void settPPH(Float tPPH) {
        this.tPPH = tPPH;
    }

    public Integer getUangSnack() {
        return uangSnack;
    }

    public void setUangSnack(Integer uangSnack) {
        this.uangSnack = uangSnack;
    }

    public Integer getTotalLembur() {
        return totalLembur;
    }

    public void setTotalLembur(Integer totalLembur) {
        this.totalLembur = totalLembur;
    }

    public Integer getTotalInfal() {
        return totalInfal;
    }

    public void setTotalInfal(Integer totalInfal) {
        this.totalInfal = totalInfal;
    }

    public Integer getAngsuranKopeg() {
        return angsuranKopeg;
    }

    public void setAngsuranKopeg(Integer angsuranKopeg) {
        this.angsuranKopeg = angsuranKopeg;
    }

    public Integer getAngsuranBank() {
        return angsuranBank;
    }

    public void setAngsuranBank(Integer angsuranBank) {
        this.angsuranBank = angsuranBank;
    }

    public Integer getAngsuranYayasan() {
        return angsuranYayasan;
    }

    public void setAngsuranYayasan(Integer angsuranYayasan) {
        this.angsuranYayasan = angsuranYayasan;
    }

    public Integer getPotonganAbsensi() {
        return potonganAbsensi;
    }

    public void setPotonganAbsensi(Integer potonganAbsensi) {
        this.potonganAbsensi = potonganAbsensi;
    }

    public Integer getPotonganPPH() {
        return potonganPPH;
    }

    public void setPotonganPPH(Integer potonganPPH) {
        this.potonganPPH = potonganPPH;
    }

    public KaryawanModel getId_karyawan() {
        return id_karyawan;
    }

    public void setId_karyawan(KaryawanModel id_karyawan) {
        this.id_karyawan = id_karyawan;
    }

    public DokumenTotalModel getDokumen() {
        return dokumen;
    }

    public void setDokumen(DokumenTotalModel dokumen) {
        this.dokumen = dokumen;
    }

    public Integer getTHPB() {
        return THPB;
    }

    public void setTHPB(Integer THPB) {
        this.THPB = THPB;
    }

    public Integer getTHPA() {
        return THPA;
    }

    public void setTHPA(Integer THPA) {
        this.THPA = THPA;
    }

    public Integer getTotalFixCost() {
        return totalFixCost;
    }

    public void setTotalFixCost(Integer totalFixCost) {
        this.totalFixCost = totalFixCost;
    }

    public Integer getTotalVarCost() {
        return totalVarCost;
    }

    public void setTotalVarCost(Integer totalVarCost) {
        this.totalVarCost = totalVarCost;
    }

    public Integer getTotalPotongan() {
        return totalPotongan;
    }

    public void setTotalPotongan(Integer totalPotongan) {
        this.totalPotongan = totalPotongan;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
