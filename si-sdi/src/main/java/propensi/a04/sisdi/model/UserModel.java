package propensi.a04.sisdi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
public class UserModel implements Serializable{

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @NotNull
    @Size(max=25)
    @Column(name = "username", nullable = false)
    private String username;

    @NotNull
    @Lob
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Column(name="unit",nullable = false)
    private Integer unit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_role", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private RoleModel id_role;

    public KaryawanModel getKaryawan() {
        return karyawan;
    }

    public void setKaryawan(KaryawanModel karyawan) {
        this.karyawan = karyawan;
    }

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private KaryawanModel karyawan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public RoleModel getId_role() {
        return id_role;
    }

    public void setId_role(RoleModel id_role) {
        this.id_role = id_role;
    }


}
