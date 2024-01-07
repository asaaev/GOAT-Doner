package com.exampleproject.GOATDONER.data;

import com.exampleproject.GOATDONER.model.Ingredients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcIngredientRepository implements IngedientRepository {


    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Ingredients> findById(String id) {
        List<Ingredients> ingredientsList = jdbcTemplate.query(
                "select id, name, type from Ingredients where id=?",
                this::mapRowToIngredients,
                id
        );
        return ingredientsList.size() == 0?
                Optional.empty() :
                Optional.of(ingredientsList.get(0));
    }

    @Override
    public Ingredients save(Ingredients ingredients) {
        jdbcTemplate.update(
                "insert into Ingredients (id, name, type) values (?, ?, ?)",
                ingredients.getId(),
                ingredients.getName(),
                ingredients.getType().toString()
        );
        return ingredients;
    }

    @Override
    public Iterable<Ingredients> findAll() {
        return jdbcTemplate.query(
                "select id, name, type from Ingredients",
                this::mapRowToIngredients
        );
    }

    private Ingredients mapRowToIngredients(ResultSet resultSet, int rowNum) throws SQLException {
            return new Ingredients(
                    resultSet.getString("id"),
                    resultSet.getString("name"),
                    Ingredients.Type.valueOf(resultSet.getString("type")));
    }
}
