package propensi.a04.sisdi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import propensi.a04.sisdi.model.SiswaModel;
import propensi.a04.sisdi.model.UserModel;

@Repository
public interface SiswaDb extends JpaRepository<SiswaModel, Long> {
    Optional<SiswaModel> findById(Long id);
    Optional<SiswaModel> findByIduserSiswa(UserModel user);
}
