package se.lexicon;

import com.mysql.cj.jdbc.ConnectionWrapper;
import se.db.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static se.db.MySQLConnection.getConnection;

public class CityDaoJDBC implements CityDao{

    @Override
    public City findById(int id) {
        City city = null;
        try (Connection conn = MySQLConnection.getConnection()){
            String query = "SELECT * FROM city WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                city = new City(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("code"),
                        resultSet.getString("district"),
                        resultSet.getInt("population")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //needs implementation
        return city;
    }

    @Override
    public City findByCode(String code) {
        List<City> cities = new ArrayList<>();
        try (Connection conn = MySQLConnection.getConnection()) {
            String query = "SELECT * FROM city WHERE code = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
               City city = new City(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("code"),
                        resultSet.getString("district"),
                        resultSet.getInt("population")
                );
               cities.add(city);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public City findByName(String name) {
        try (Connection conn = MySQLConnection.getConnection()){
            String query = "SELECT * FROM city WHERE name = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                City city = new City(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("code"),
                        resultSet.getString("district"),
                        resultSet.getInt("population")
                );
                return city;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        //needs implementation
        return null;
    }

    @Override
    public City add(City city) {
        try (Connection conn = MySQLConnection.getConnection()){
            String query = "INSERT INTO city (name, code, district, population) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getPopulation());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 1){
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()){
                    city.setId(resultSet.getInt(1));
                }
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        //needs implementation
        return null;
    }

    @Override
    public City update(City city) {
        try (Connection conn = MySQLConnection.getConnection()){
            String query = "UPDATE city SET name = ?, code = ?, district = ?, population = ? WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getPopulation());
            preparedStatement.setInt(5, city.getId());

                preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        //needs implementation
        return city;
    }

    @Override
    public int delete(City city) {
        try (Connection conn = MySQLConnection.getConnection()){
            String query = "DELETE FROM city WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, city.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected;

        } catch (SQLException e){
            e.printStackTrace();
        }
        //needs implementation
        return 0;
    }

    @Override
    public List<City> findAll() {
        try (Connection conn = MySQLConnection.getConnection()){
            String query = "SELECT * FROM city";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            List<City> cities = new ArrayList<>();
            while (resultSet.next()){
                City city = new City(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("code"),
                        resultSet.getString("district"),
                        resultSet.getInt("population")

                );
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        //needs implementation
        return null;
    }
}
