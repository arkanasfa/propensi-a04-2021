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
@Table(name="borangPenilaian")
public class BorangPenilaianModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="namaBorang",nullable = false)
    private String namaBorang;

    @OneToMany(mappedBy = "id_listPertanyaan", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<PertanyaanModel> listPertanyaanBorang;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaBorang() {
        return namaBorang;
    }

    public void setNamaBorang(String namaBorang) {
        this.namaBorang = namaBorang;
    }

    public List<PertanyaanModel> getListPertanyaanBorang() {
        return listPertanyaanBorang;
    }

    public void setListPertanyaanBorang(List<PertanyaanModel> listPertanyaanBorang) {
        this.listPertanyaanBorang = listPertanyaanBorang;
    }
}
