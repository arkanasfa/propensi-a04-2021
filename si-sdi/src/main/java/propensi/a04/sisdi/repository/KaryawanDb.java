package propensi.a04.sisdi.repository;

import propensi.a04.sisdi.model.GajiModel;
import propensi.a04.sisdi.model.KaryawanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface KaryawanDb extends JpaRepository<KaryawanModel,Integer>{
    Optional<KaryawanModel> findById(Long id_karyawan);
    Optional<KaryawanModel> findByGajiModel(GajiModel gaji);
}
