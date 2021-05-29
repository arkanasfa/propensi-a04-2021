package propensi.a04.sisdi.service;

import java.util.List;

import propensi.a04.sisdi.model.SiswaModel;

public interface SiswaService {
    void addSiswa(SiswaModel siswaModel);
    List<SiswaModel> getListSiswa();
    SiswaModel getSiswaById(Long id);

}
