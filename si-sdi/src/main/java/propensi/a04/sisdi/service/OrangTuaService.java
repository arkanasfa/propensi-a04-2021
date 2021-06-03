package propensi.a04.sisdi.service;

import propensi.a04.sisdi.model.OrangTuaModel;
import propensi.a04.sisdi.model.UserModel;

public interface OrangTuaService {
    void addOrangTua(OrangTuaModel orangTuaModel);
    OrangTuaModel getOrangTuaById(Long id);
    OrangTuaModel getByIduser(UserModel user);
}
