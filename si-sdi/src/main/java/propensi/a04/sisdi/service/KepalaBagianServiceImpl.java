package propensi.a04.sisdi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import propensi.a04.sisdi.model.KepalaBagianModel;
import propensi.a04.sisdi.model.ManajerPendidikanModel;
import propensi.a04.sisdi.repository.KepalaBagianDb;

import javax.transaction.Transactional;

@Service
@Transactional
public class KepalaBagianServiceImpl implements KepalaBagianService{
    @Autowired
    KepalaBagianDb kepalaBagianDb;

    @Override
    public void addKepalaBagian(KepalaBagianModel KB) {
        kepalaBagianDb.save(KB);
    }
}
