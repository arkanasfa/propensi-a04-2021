package propensi.a04.sisdi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="karyawan")
public class KaryawanModel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=25)
    @Column(name="karyawan",nullable = false)
    private String karyawan;

    @NotNull
    @Column(name="noKaryawan",nullable = false)
    private Long noKaryawan;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="tanggalMasuk",nullable = false)
    private Date tanggalMasuk;

    @NotNull
    @Column(name="jumlahAnak",nullable = false)
    private Integer jumlahAnak;

    @NotNull
    @Column(name="statusMenikah",nullable = false)
    private Integer statusMenikah;

    @NotNull
    @Column(name="skorPerforma",nullable = false)
    private Integer skorPerforma;

    @NotNull
    @Column(name="skorPengaduan",nullable = false)
    private Integer skorPengaduan;

    @NotNull
    @Column(name="statusKaryawan",nullable = false)
    private Integer statusKaryawan;

    @NotNull
    @Column(name="gaji",nullable = false)
    private Long gaji;

    @NotNull
    @Column(name="jumlahCuti",nullable = false)
    private Integer jumlahCuti;

    @NotNull
    @Column(name="jumlahAbsensi",nullable = false)
    private Integer jumlahAbsensi;

    @NotNull
    @Column(name="jumlahLembur",nullable = false)
    private Integer jumlahLembur;

    @NotNull
    @Size(max=50)
    @Column(name="email",nullable = false)
    private String email;

    @NotNull
    @Size(max=15)
    @Column(name="unit",nullable=false)
    private String unit;

    @NotNull
    @Column(name="golongan",nullable=false)
    private Long golongan;

    @OneToOne(mappedBy = "id_karyawan")
    private WakilPengurusHarianModel wakilpengurusHarianModel;

    @OneToOne(mappedBy = "id_karyawan")
    private PengurusHarianModel pengurusHarianModel;

    @OneToOne(mappedBy = "id_karyawan")
    private ManajerPendidikanModel manajerPendidikanModel;

    @OneToOne(mappedBy = "id_karyawan")
    private PimpinanUnitModel pimpinanUnitModel;

    @OneToOne(mappedBy = "id_karyawan")
    private ManajerSDIModel manajerSDIModel;

    @OneToOne(mappedBy = "id_karyawan")
    private KepalaBagianModel kepalaBagianModel;

    @OneToOne(mappedBy = "id_karyawan")
    private GuruModel guruModel;

    @OneToMany(mappedBy = "no_karyawan", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<PengaduanModel> listpengaduanKaryawan;

    @OneToOne(mappedBy = "id_karyawan")
    private GajiModel gajiModel;

//    @OneToMany(mappedBy = "id_karyawan", fetch = FetchType.LAZY)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private List<PengajuanCutiModel> listPengajuanCuti;

    @OneToMany(mappedBy = "id_karyawan", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<LemburModel> listLembur;

    @OneToOne(mappedBy = "id_infal")
    private LemburModel infal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKaryawan() {
        return karyawan;
    }

    public void setKaryawan(String karyawan) {
        this.karyawan = karyawan;
    }

    public Long getNoKaryawan() {
        return noKaryawan;
    }

    public void setNoKaryawan(Long noKaryawan) {
        this.noKaryawan = noKaryawan;
    }

    public Date getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setTanggalMasuk(Date tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    public Integer getJumlahAnak() {
        return jumlahAnak;
    }

    public void setJumlahAnak(Integer jumlahAnak) {
        this.jumlahAnak = jumlahAnak;
    }

    public Integer getStatusMenikah() {
        return statusMenikah;
    }

    public void setStatusMenikah(Integer statusMenikah) {
        this.statusMenikah = statusMenikah;
    }

    public Integer getSkorPerforma() {
        return skorPerforma;
    }

    public void setSkorPerforma(Integer skorPerforma) {
        this.skorPerforma = skorPerforma;
    }

    public Integer getSkorPengaduan() {
        return skorPengaduan;
    }

    public void setSkorPengaduan(Integer skorPengaduan) {
        this.skorPengaduan = skorPengaduan;
    }

    public Integer getStatusKaryawan() {
        return statusKaryawan;
    }

    public void setStatusKaryawan(Integer statusKaryawan) {
        this.statusKaryawan = statusKaryawan;
    }

    public Long getGaji() {
        return gaji;
    }

    public void setGaji(Long gaji) {
        this.gaji = gaji;
    }

    public Integer getJumlahCuti() {
        return jumlahCuti;
    }

    public void setJumlahCuti(Integer jumlahCuti) {
        this.jumlahCuti = jumlahCuti;
    }

    public Integer getJumlahAbsensi() {
        return jumlahAbsensi;
    }

    public void setJumlahAbsensi(Integer jumlahAbsensi) {
        this.jumlahAbsensi = jumlahAbsensi;
    }

    public Integer getJumlahLembur() {
        return jumlahLembur;
    }

    public void setJumlahLembur(Integer jumlahLembur) {
        this.jumlahLembur = jumlahLembur;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public WakilPengurusHarianModel getWakilpengurusHarianModel() {
        return wakilpengurusHarianModel;
    }

    public void setWakilpengurusHarianModel(WakilPengurusHarianModel wakilpengurusHarianModel) {
        this.wakilpengurusHarianModel = wakilpengurusHarianModel;
    }

    public PengurusHarianModel getPengurusHarianModel() {
        return pengurusHarianModel;
    }

    public void setPengurusHarianModel(PengurusHarianModel pengurusHarianModel) {
        this.pengurusHarianModel = pengurusHarianModel;
    }

    public ManajerPendidikanModel getManajerPendidikanModel() {
        return manajerPendidikanModel;
    }

    public void setManajerPendidikanModel(ManajerPendidikanModel manajerPendidikanModel) {
        this.manajerPendidikanModel = manajerPendidikanModel;
    }

    public PimpinanUnitModel getPimpinanUnitModel() {
        return pimpinanUnitModel;
    }

    public void setPimpinanUnitModel(PimpinanUnitModel pimpinanUnitModel) {
        this.pimpinanUnitModel = pimpinanUnitModel;
    }

    public ManajerSDIModel getManajerSDIModel() {
        return manajerSDIModel;
    }

    public void setManajerSDIModel(ManajerSDIModel manajerSDIModel) {
        this.manajerSDIModel = manajerSDIModel;
    }

    public KepalaBagianModel getKepalaBagianModel() {
        return kepalaBagianModel;
    }

    public void setKepalaBagianModel(KepalaBagianModel kepalaBagianModel) {
        this.kepalaBagianModel = kepalaBagianModel;
    }

    public GuruModel getGuruModel() {
        return guruModel;
    }

    public void setGuruModel(GuruModel guruModel) {
        this.guruModel = guruModel;
    }

    public List<PengaduanModel> getListpengaduanKaryawan() {
        return listpengaduanKaryawan;
    }

    public void setListpengaduanKaryawan(List<PengaduanModel> listpengaduanKaryawan) {
        this.listpengaduanKaryawan = listpengaduanKaryawan;
    }

    public GajiModel getGajiModel() {
        return gajiModel;
    }

    public void setGajiModel(GajiModel gajiModel) {
        this.gajiModel = gajiModel;
    }

//    public List<PengajuanCutiModel> getListPengajuanCuti() {
//        return listPengajuanCuti;
//    }
//
//    public void setListPengajuanCuti(List<PengajuanCutiModel> listPengajuanCuti) {
//        this.listPengajuanCuti = listPengajuanCuti;
//    }

    public List<LemburModel> getListLembur() {
        return listLembur;
    }

    public void setListLembur(List<LemburModel> listLembur) {
        this.listLembur = listLembur;
    }

    public LemburModel getInfal() {
        return infal;
    }

    public void setInfal(LemburModel infal) {
        this.infal = infal;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getGolongan() {
        return golongan;
    }

    public void setGolongan(Long golongan) {
        this.golongan = golongan;
    }

}
