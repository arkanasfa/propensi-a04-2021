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
@Table(name="pasanganModel")
public class PasanganModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_gurupengaju", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private GuruModel id_gurupengaju;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_guruprioritas1", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private GuruModel id_guruprioritas1;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_guruprioritas2", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private GuruModel id_guruprioritas2;

    @NotNull
    @Column(name="status",nullable = false)
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GuruModel getId_gurupengaju() {
        return id_gurupengaju;
    }

    public void setId_gurupengaju(GuruModel id_gurupengaju) {
        this.id_gurupengaju = id_gurupengaju;
    }

    public GuruModel getId_guruprioritas1() {
        return id_guruprioritas1;
    }

    public void setId_guruprioritas1(GuruModel id_guruprioritas1) {
        this.id_guruprioritas1 = id_guruprioritas1;
    }

    public GuruModel getId_guruprioritas2() {
        return id_guruprioritas2;
    }

    public void setId_guruprioritaa2(GuruModel id_guruprioritaa2) {
        this.id_guruprioritas2 = id_guruprioritaa2;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setId_guruprioritas2(GuruModel id_guruprioritas2) {
        this.id_guruprioritas2 = id_guruprioritas2;
    }
}
