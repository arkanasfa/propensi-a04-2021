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
@Table(name="kepalaBagian")
public class KepalaBagianModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotNull
    //@Column(name="identifikasiDivPendidikan",nullable = false)
    //private Boolean identifikasiDivPendidikan;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idkaryawan", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private KaryawanModel idkaryawan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //public Boolean getIdentifikasiDivPendidikan() {
    //    return identifikasiDivPendidikan;
    //}

    //public void setIdentifikasiDivPendidikan(Boolean identifikasiDivPendidikan) {
    //    this.identifikasiDivPendidikan = identifikasiDivPendidikan;
    //}

    public KaryawanModel getId_karyawan() {
        return idkaryawan;
    }

    public void setId_karyawan(KaryawanModel id_karyawan) {
        this.idkaryawan = id_karyawan;
    }


}
