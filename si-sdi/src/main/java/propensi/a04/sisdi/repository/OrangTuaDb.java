package propensi.a04.sisdi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import propensi.a04.sisdi.model.OrangTuaModel;
import propensi.a04.sisdi.model.UserModel;

@Repository
public interface OrangTuaDb extends JpaRepository<OrangTuaModel, Long> {
    Optional<OrangTuaModel> findById(Long id);
    Optional<OrangTuaModel>findByIduserOrtu(UserModel user);
}
