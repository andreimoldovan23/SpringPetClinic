package com.springpetclinic.data.services.mapBased;

import com.springpetclinic.data.model.Entity;
import com.springpetclinic.data.services.CrudService;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMapService<T, ID> implements CrudService<T, ID> {

    protected Map<ID, T> map = new HashMap<>();

    public T findById(ID id) {
        return map.get(id);
    }

    public Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    @SuppressWarnings("unchecked")
    public T save(T type) {
        map.put((ID) ((Entity)type).getId(), type);
        return type;
    }

    public void delete(T type) {
        map.entrySet().removeIf(e -> e.getValue().equals(type));
    }

    public void deleteById(ID id) {
        map.remove(id);
    }

}
