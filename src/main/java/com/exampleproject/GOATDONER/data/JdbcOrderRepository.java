package com.exampleproject.GOATDONER.data;

import com.exampleproject.GOATDONER.model.Doner;
import com.exampleproject.GOATDONER.model.DonerOrder;
import com.exampleproject.GOATDONER.model.Ingredients;
import com.exampleproject.GOATDONER.utility.IngredientsRef;
import org.springframework.asm.Type;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    JdbcOperations jdbcOperations;

    public JdbcOrderRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    @Transactional
    public DonerOrder save(DonerOrder order) {
        PreparedStatementCreatorFactory preparedStatementCreatorFactory = new PreparedStatementCreatorFactory(
                "insert into Doner_Order "
                        + "(delivery_name, delivery_street, delivery_city, "
                        + "delivery_state, delivery_zip, cc_number, "
                        + "cc_expiration, cc_cvv, placed_at) "
                        + "values (?,?,?,?,?,?,?,?,?)",
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
        );
        preparedStatementCreatorFactory.setReturnGeneratedKeys(true);

        order.setPlacedAt(new Date(System.currentTimeMillis()));
        PreparedStatementCreator psc =
                preparedStatementCreatorFactory.newPreparedStatementCreator(
                        Arrays.asList(
                                order.getDeliveryName(),
                                order.getDeliveryStreet(),
                                order.getDeliveryCity(),
                                order.getDeliveryState(),
                                order.getDeliveryZip(),
                                order.getCcNumber(),
                                order.getCcExpiration(),
                                order.getCcCVV(),
                                order.getPlacedAt()));
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long orderId = keyHolder.getKey().longValue();
        order.setId(orderId);
        List<Doner> donerList = order.getDonerList();
        int i = 0;
        for (Doner doner : donerList) {
            saveDoner(orderId, i++, doner);
        }
        return order;
    }

    private long saveDoner(long orderId, int orderKey, Doner doner) {
        doner.setCreatedAt(new Date(System.currentTimeMillis()));
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "insert into Doner "
                        + "(name, created_at, doner_order, doner_order_key) "
                        + "values (?, ?, ?, ?)",
                Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG
        );
        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(
                        doner.getName(),
                        doner.getCreatedAt(),
                        orderId,
                        orderKey)
        );
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long donerId = keyHolder.getKey().longValue();
        doner.setId(donerId);

        saveIngredientsRefs(donerId, doner.getIngredient());
        return donerId;
    }

    private void saveIngredientsRefs(long donerId, List<IngredientsRef> ingredientRefs) {
        int key = 0;
        for (IngredientsRef ingredientRef : ingredientRefs){
            jdbcOperations.update(
                    "insert into Ingredients_Ref (ingredients, doner, doner_key) "
                            + "values (?, ?, ?)",
                    ingredientRef.getIngredient(), donerId, key++);
        }
    }

    @Override
    public <S extends DonerOrder> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<DonerOrder> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<DonerOrder> findAll() {
        return null;
    }

    @Override
    public Iterable<DonerOrder> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(DonerOrder entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends DonerOrder> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
