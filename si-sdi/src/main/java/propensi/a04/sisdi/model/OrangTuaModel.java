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

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getNamaOrangTua() {
                return namaOrangTua;
        }

        public void setNamaOrangTua(String namaOrangTua) {
                this.namaOrangTua = namaOrangTua;
        }

        public Integer getJenisKelamin() {
                return jenisKelamin;
        }

        public void setJenisKelamin(Integer jenisKelamin) {
                this.jenisKelamin = jenisKelamin;
        }

        public SiswaModel getId_siswa() {
                return id_siswa;
        }

        public void setId_siswa(SiswaModel id_siswa) {
                this.id_siswa = id_siswa;
        }
}
