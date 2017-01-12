package com.fi.ls.mapping;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.dozer.Mapper;

/**
 * @author Pavel Šeda (441048)
 *
 */
public interface BeanMapping {

	public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);
        
        public <T> Set<T> mapToSet(Collection<?> objects, Class<T> mapToClass);

	public <T> Optional<T> mapTo(Object u, Class<T> mapToClass);

}
