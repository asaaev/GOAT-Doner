package com.exampleproject.GOATDONER.data;

import com.exampleproject.GOATDONER.model.DonerOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends CrudRepository<DonerOrder, Long> {

    @Transactional
    DonerOrder save(DonerOrder order);

}
