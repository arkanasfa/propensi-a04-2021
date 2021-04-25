package propensi.a04.sisdi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import propensi.a04.sisdi.model.KaryawanModel;
import propensi.a04.sisdi.model.UserModel;

@Repository
public interface KaryawanDB extends JpaRepository<KaryawanModel, Long> {
    KaryawanModel findByUser(UserModel user);
}
