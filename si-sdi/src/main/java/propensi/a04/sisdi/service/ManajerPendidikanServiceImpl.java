package propensi.a04.sisdi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import propensi.a04.sisdi.model.ManajerPendidikanModel;
import propensi.a04.sisdi.model.PimpinanUnitModel;
import propensi.a04.sisdi.repository.ManajerPendidikanDb;

import javax.transaction.Transactional;

@Service
@Transactional
public class ManajerPendidikanServiceImpl implements ManajerPendidikanService{
    @Autowired
    ManajerPendidikanDb manajerPendidikanDb;

    @Override
    public void addManajerPendidikan(ManajerPendidikanModel MP) {
        manajerPendidikanDb.save(MP);
    }
}
