package com.springpetclinic.data.services.datajpaBased;

import com.springpetclinic.data.exceptions.MyException;
import com.springpetclinic.data.model.BaseEntity;
import com.springpetclinic.data.services.CrudService;
import org.springframework.data.repository.CrudRepository;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractJPAService<T extends BaseEntity, ID extends Long> implements CrudService<T, ID> {

    protected final CrudRepository<T, ID> repository;

    protected AbstractJPAService(CrudRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public T save(T type) throws MyException {
        return repository.save(type);
    }

    @Override
    public Set<T> findAll() {
        Set<T> set = new HashSet<>();
        repository.findAll().forEach(set::add);
        return set;
    }

    @Override
    public T findById(ID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void delete(T type) {
        repository.delete(type);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

}
