package workshop.task_planner.service;

import java.io.Serializable;
import java.util.Collection;

public interface BaseService<D, I extends Serializable> {

      D save(D dto);

      D update(D dto,I id);

      D find(I id);

      Boolean remove(I id);

      Collection<D> getAll();

}
