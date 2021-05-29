package propensi.a04.sisdi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import propensi.a04.sisdi.model.KaryawanModel;
import propensi.a04.sisdi.model.PimpinanUnitModel;
import propensi.a04.sisdi.repository.PimpinanUnitDb;

import javax.transaction.Transactional;

@Service
@Transactional
public class PimpinanUnitServiceImpl implements PimpinanUnitService{
    @Autowired
    private PimpinanUnitDb pimpinanUnitDb;

    @Override
    public PimpinanUnitModel getPimpinanUnitByIdKaryawan(KaryawanModel karyawan) {
        return pimpinanUnitDb.findByIdkaryawan(karyawan).get();
    }

    @Override
    public void addPimpinanUnit(PimpinanUnitModel PU) {
        pimpinanUnitDb.save(PU);
    }
}
