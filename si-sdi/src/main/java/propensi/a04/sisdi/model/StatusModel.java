package propensi.a04.sisdi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="status")
public class StatusModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=30)
    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(mappedBy = "id_status", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<GuruSertifikasiModel> statusSertifikasi;

    @OneToMany(mappedBy = "id_status", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<PengaduanModel> statusPengaduan;

    @OneToMany(mappedBy = "id_status", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<DokumenTotalModel> statusDokumen;

    @OneToMany(mappedBy = "idstatus", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<PengajuanCutiModel> statusCuti;

    @OneToMany(mappedBy = "id_status", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<LemburModel> statusLembur;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GuruSertifikasiModel> getStatusSertifikasi() {
        return statusSertifikasi;
    }

    public void setStatusSertifikasi(List<GuruSertifikasiModel> statusSertifikasi) {
        this.statusSertifikasi = statusSertifikasi;
    }

    public List<PengaduanModel> getStatusPengaduan() {
        return statusPengaduan;
    }

    public void setStatusPengaduan(List<PengaduanModel> statusPengaduan) {
        this.statusPengaduan = statusPengaduan;
    }

    public List<DokumenTotalModel> getStatusDokumen() {
        return statusDokumen;
    }

    public void setStatusDokumen(List<DokumenTotalModel> statusDokumen) {
        this.statusDokumen = statusDokumen;
    }

    public List<PengajuanCutiModel> getStatusCuti() {
        return statusCuti;
    }

    public void setStatusCuti(List<PengajuanCutiModel> statusCuti) {
        this.statusCuti = statusCuti;
    }

    public List<LemburModel> getStatusLembur() {
        return statusLembur;
    }

    public void setStatusLembur(List<LemburModel> statusLembur) {
        this.statusLembur = statusLembur;
    }
}
