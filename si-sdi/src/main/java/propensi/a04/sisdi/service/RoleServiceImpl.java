package propensi.a04.sisdi.service;

import propensi.a04.sisdi.model.RoleModel;
import propensi.a04.sisdi.repository.RoleDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDb roleDb;

    @Override
    public List<RoleModel> findAll() {
        return roleDb.findAll();
    }

    @Override
    public RoleModel findRolebyId(Long id){return roleDb.findById(id).get();}
}
