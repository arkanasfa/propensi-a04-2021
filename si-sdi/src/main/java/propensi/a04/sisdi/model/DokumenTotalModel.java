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
@Table(name="dokumenTotalAnggaran")
public class DokumenTotalModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="tanggalIsu",nullable = false)
    private Date tanggalIsu;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_status", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private StatusModel id_status;

    @OneToMany(mappedBy = "id_dokumen", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<GajiModel> listGaji;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTanggalIsu() {
        return tanggalIsu;
    }

    public void setTanggalIsu(Date tanggalIsu) {
        this.tanggalIsu = tanggalIsu;
    }

    public StatusModel getId_status() {
        return id_status;
    }

    public void setId_status(StatusModel id_status) {
        this.id_status = id_status;
    }

    public List<GajiModel> getListGaji() {
        return listGaji;
    }

    public void setListGaji(List<GajiModel> listGaji) {
        this.listGaji = listGaji;
    }
}
