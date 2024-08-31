package se.lexicon;

import java.util.List;

public interface CityDao {

    City findById(int id);
    City findByCode(String code);
    City findByName(String name);
    City add(City city);
    City update(City city);
    int delete(City city);
    List<City> findAll();
}
