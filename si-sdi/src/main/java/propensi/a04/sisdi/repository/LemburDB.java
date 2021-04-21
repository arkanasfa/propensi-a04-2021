package propensi.a04.sisdi.repository;

import propensi.a04.sisdi.model.LemburModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LemburDB extends JpaRepository<LemburModel,Long>{
    Optional<LemburModel> findById(Long id);
}
