package propensi.a04.sisdi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import propensi.a04.sisdi.model.ManajerSDIModel;
import propensi.a04.sisdi.repository.ManajerSDIDb;

import javax.transaction.Transactional;

@Service
@Transactional
public class ManajerSDIServiceImpl implements ManajerSDIService{
    @Autowired
    ManajerSDIDb manajerSDIDb;
    @Override
    public void addManajerSDI(ManajerSDIModel MSDI) {
        manajerSDIDb.save(MSDI);
    }
}
