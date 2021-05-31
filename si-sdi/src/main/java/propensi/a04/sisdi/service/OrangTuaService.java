package propensi.a04.sisdi.service;

import propensi.a04.sisdi.model.OrangTuaModel;

public interface OrangTuaService {
    void addOrangTua(OrangTuaModel orangTuaModel);
    OrangTuaModel getOrangTuaById(Long id);
}
