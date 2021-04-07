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
@Table(name="jawabanBorang")
public class JawabanBorangModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_guru", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private GuruModel id_guru;

    @OneToMany(mappedBy = "id_listJawaban", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<JawabanModel> listJawabanBorang;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GuruModel getId_guru() {
        return id_guru;
    }

    public void setId_guru(GuruModel id_guru) {
        this.id_guru = id_guru;
    }

    public List<JawabanModel> getListJawabanBorang() {
        return listJawabanBorang;
    }

    public void setListJawabanBorang(List<JawabanModel> listJawabanBorang) {
        this.listJawabanBorang = listJawabanBorang;
    }
}
