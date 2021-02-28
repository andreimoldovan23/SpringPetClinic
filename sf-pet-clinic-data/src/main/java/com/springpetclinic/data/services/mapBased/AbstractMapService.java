package com.springpetclinic.data.services.mapBased;

import com.springpetclinic.data.exceptions.MyException;
import com.springpetclinic.data.exceptions.NullObject;
import com.springpetclinic.data.model.Entity;
import com.springpetclinic.data.services.CrudService;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@SuppressWarnings("FieldMayBeFinal")
public abstract class AbstractMapService<T extends Entity, ID extends Long> implements CrudService<T, ID> {

    protected Map<ID, T> map = new ConcurrentHashMap<>();
    protected AtomicLong id = new AtomicLong(0);

    public T findById(ID id) {
        return map.get(id);
    }

    public Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    @SuppressWarnings("unchecked")
    public T save(T type) throws MyException {
        if(type == null)
            throw new NullObject();

        if(type.getId() == null) {
            Long typeId = id.incrementAndGet();
            type.setId(typeId);
        }
        map.put((ID) type.getId(), type);
        return type;
    }

    public void delete(T type) {
        map.entrySet().removeIf(e -> e.getValue().equals(type));
    }

    public void deleteById(ID id) {
        map.remove(id);
    }

}
