package propensi.a04.sisdi.repository;

import propensi.a04.sisdi.model.KaryawanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface KaryawanDB extends JpaRepository<KaryawanModel,Integer>{
    Optional<KaryawanModel> findById(Long id_karyawan);
}
