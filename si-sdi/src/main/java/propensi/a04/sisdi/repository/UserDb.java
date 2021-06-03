package propensi.a04.sisdi.repository;

import propensi.a04.sisdi.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserDb extends JpaRepository<UserModel, String> {
    UserModel findByUsername(String Username);
    List<UserModel> findAll();

}
