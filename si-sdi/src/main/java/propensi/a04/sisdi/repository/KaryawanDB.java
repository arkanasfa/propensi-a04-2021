package propensi.a04.sisdi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import propensi.a04.sisdi.model.KaryawanModel;
import propensi.a04.sisdi.model.UserModel;
import java.util.Optional;

@Repository
public interface KaryawanDB extends JpaRepository<KaryawanModel, Integer> {
    KaryawanModel findByUser(UserModel user);
    Optional<KaryawanModel> findById(Long id_karyawan);
}
