package propensi.a04.sisdi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import propensi.a04.sisdi.model.GajiModel;
import propensi.a04.sisdi.model.KaryawanModel;
import propensi.a04.sisdi.model.UserModel;
import java.util.List;
import java.util.Optional;

@Repository
public interface KaryawanDb extends JpaRepository<KaryawanModel,Long> {
    List<KaryawanModel> findAll();
    //KaryawanModel findByUser(UserModel user);
    Optional<KaryawanModel> findByGajiModel(GajiModel gaji);
	Optional<KaryawanModel> findByNoKaryawan(Long no_karyawan);
   
}

