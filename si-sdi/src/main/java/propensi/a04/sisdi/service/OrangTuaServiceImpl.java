package propensi.a04.sisdi.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import propensi.a04.sisdi.model.OrangTuaModel;
import propensi.a04.sisdi.model.UserModel;
import propensi.a04.sisdi.repository.OrangTuaDb;

@Service
@Transactional
public class OrangTuaServiceImpl implements OrangTuaService {
    @Autowired
    OrangTuaDb orangTuaDb;

    @Override
    public void addOrangTua(OrangTuaModel orangtua){orangTuaDb.save(orangtua);}


    @Override
    public OrangTuaModel getOrangTuaById(Long id) {

        return orangTuaDb.findById(id).get();
    }

    @Override
    public OrangTuaModel getByIduser(UserModel user) {
        return orangTuaDb.findByIduserOrtu(user).get();
    }
}
