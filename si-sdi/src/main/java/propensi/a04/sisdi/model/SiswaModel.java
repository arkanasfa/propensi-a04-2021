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
@Table(name="siswa")
public class SiswaModel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=25)
    @Column(name="namaSiswa",nullable = false)
    private String namaSiswa;

    @NotNull
    @Column(name="jenisKelamin",nullable = false)
    private Integer jenisKelamin;

    @NotNull
    @Column(name="kelas",nullable = false)
    private String kelas;

    @OneToOne(mappedBy = "id_siswa")
    private OrangTuaModel orangTuaModel;

    @OneToMany(mappedBy = "idSiswa_pengadu", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<PengaduanModel> listPengaduan;

    

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
     * @return String return the namaSiswa
     */
    public String getNamaSiswa() {
        return namaSiswa;
    }

    /**
     * @param namaSiswa the namaSiswa to set
     */
    public void setNamaSiswa(String namaSiswa) {
        this.namaSiswa = namaSiswa;
    }

    /**
     * @return Integer return the jenisKelamin
     */
    public Integer getJenisKelamin() {
        return jenisKelamin;
    }

    /**
     * @param jenisKelamin the jenisKelamin to set
     */
    public void setJenisKelamin(Integer jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    /**
     * @return String return the kelas
     */
    public String getKelas() {
        return kelas;
    }

    /**
     * @param kelas the kelas to set
     */
    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    /**
     * @return OrangTuaModel return the orangTuaModel
     */
    public OrangTuaModel getOrangTuaModel() {
        return orangTuaModel;
    }

    /**
     * @param orangTuaModel the orangTuaModel to set
     */
    public void setOrangTuaModel(OrangTuaModel orangTuaModel) {
        this.orangTuaModel = orangTuaModel;
    }

    /**
     * @return List<PengaduanModel> return the listPengaduan
     */
    public List<PengaduanModel> getListPengaduan() {
        return listPengaduan;
    }

    /**
     * @param listPengaduan the listPengaduan to set
     */
    public void setListPengaduan(List<PengaduanModel> listPengaduan) {
        this.listPengaduan = listPengaduan;
    }

}
