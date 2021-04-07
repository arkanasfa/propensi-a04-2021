package propensi.a04.sisdi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="pertanyaan")
public class PertanyaanModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=250)
    @Column(name="jawaban",nullable = false)
    private String jawaban;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_listPertanyaan", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private BorangPenilaianModel id_listPertanyaan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }

    public BorangPenilaianModel getId_listPertanyaan() {
        return id_listPertanyaan;
    }

    public void setId_listPertanyaan(BorangPenilaianModel id_listPertanyaan) {
        this.id_listPertanyaan = id_listPertanyaan;
    }
}
