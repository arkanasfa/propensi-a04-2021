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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToOne(mappedBy = "id_user")
    private KaryawanModel karyawanModel;


//    public KaryawanModel getKaryawan() {
//        return karyawan;
//    }
//
//    public void setKaryawan(KaryawanModel karyawan) {
//        this.karyawan = karyawan;
//    }
//
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private KaryawanModel karyawan;

    // @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    // @OnDelete(action = OnDeleteAction.CASCADE)
    // @JsonIgnore
    // private List<PengaduanModel> listPengaduan;

   /*  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<PengaduanModel> listPengaduan; */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public KaryawanModel getKaryawanModel() {
        return karyawanModel;
    }

    public void setKaryawanModel(KaryawanModel karyawanModel) {
        this.karyawanModel = karyawanModel;
    }

    /*
    public List<PengaduanModel> getListPengaduan() {
        return listPengaduan;
    }

  
    public void setListPengaduan(List<PengaduanModel> listPengaduan) {
        this.listPengaduan = listPengaduan;
    }
 */
}
