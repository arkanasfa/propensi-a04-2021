package propensi.a04.sisdi.service;

import java.util.List;
import propensi.a04.sisdi.model.RoleModel;

public interface RoleService {
    List<RoleModel> findAll();
    RoleModel findRolebyId(Long id);
}
