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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaSiswa() {
        return namaSiswa;
    }

    public void setNamaSiswa(String namaSiswa) {
        this.namaSiswa = namaSiswa;
    }

    public Integer getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(Integer jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public OrangTuaModel getOrangTuaModel() {
        return orangTuaModel;
    }

    public void setOrangTuaModel(OrangTuaModel orangTuaModel) {
        this.orangTuaModel = orangTuaModel;
    }
}
