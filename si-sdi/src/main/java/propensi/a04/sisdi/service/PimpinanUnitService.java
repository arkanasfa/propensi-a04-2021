package propensi.a04.sisdi.service;

import propensi.a04.sisdi.model.KaryawanModel;
import propensi.a04.sisdi.model.PimpinanUnitModel;

import java.util.List;

public interface PimpinanUnitService {
    PimpinanUnitModel getPimpinanUnitByIdKaryawan(KaryawanModel karyawan);
    void addPimpinanUnit(PimpinanUnitModel PU);
}
