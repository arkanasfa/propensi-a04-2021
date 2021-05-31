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

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id_userOrtu", referencedColumnName = "id")
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JsonIgnore
        private UserModel id_userOrtu;
        
        

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
     * @return UserModel return the id_userOrtu
     */
    public UserModel getId_userOrtu() {
        return id_userOrtu;
    }

    /**
     * @param id_userOrtu the id_userOrtu to set
     */
    public void setId_userOrtu(UserModel id_userOrtu) {
        this.id_userOrtu = id_userOrtu;
    }

}
