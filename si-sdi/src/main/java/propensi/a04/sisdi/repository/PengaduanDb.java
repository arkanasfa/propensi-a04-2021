package propensi.a04.sisdi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import propensi.a04.sisdi.model.PengaduanModel;
import propensi.a04.sisdi.model.UserModel;

@Repository
public interface PengaduanDb extends JpaRepository<PengaduanModel, Long> {
    Optional<PengaduanModel> findById(Long id);
    //List<PengaduanModel> findByUser(UserModel user);

}
