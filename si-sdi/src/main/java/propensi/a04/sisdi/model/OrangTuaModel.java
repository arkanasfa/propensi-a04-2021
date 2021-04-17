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
@Table(name="OrangTua")
public class OrangTuaModel implements Serializable{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotNull
        @Size(max=25)
        @Column(name="namaOrangTua",nullable = false)
        private String namaOrangTua;

        @NotNull
        @Column(name="jenisKelamin",nullable = false)
        private Integer jenisKelamin;

        @OneToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "id_siswa", referencedColumnName = "id", nullable = false)
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JsonIgnore
        private SiswaModel id_siswa;

        @OneToMany(mappedBy = "idOrtu_pengadu", fetch = FetchType.LAZY)
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
     * @return String return the namaOrangTua
     */
    public String getNamaOrangTua() {
        return namaOrangTua;
    }

    /**
     * @param namaOrangTua the namaOrangTua to set
     */
    public void setNamaOrangTua(String namaOrangTua) {
        this.namaOrangTua = namaOrangTua;
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
     * @return SiswaModel return the id_siswa
     */
    public SiswaModel getId_siswa() {
        return id_siswa;
    }

    /**
     * @param id_siswa the id_siswa to set
     */
    public void setId_siswa(SiswaModel id_siswa) {
        this.id_siswa = id_siswa;
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
