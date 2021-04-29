package dev.tribble.database;

import dev.tribble.model.Lab;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LabDAO {

    private final Connection CONNECTION;
    private static final String LAB_NULL_MESSAGE = "Parameter lab cannot be null.";

    public LabDAO(){
        this.CONNECTION = DBConnection.INSTANCE.getConnection();
    }

    /**
     * Saves a new Lab to the database
     * @param lab The Lab to be saved
     * @return True if saved successfully false if not.
     * @throws SQLException Thrown if something goes wrong with the database and connection
     */
    public boolean saveLab(Lab lab) throws SQLException {
        if (lab == null)
            throw new IllegalArgumentException(LAB_NULL_MESSAGE);
        else if (lab.getLabId() != 0)
            throw new IllegalArgumentException("Can only save new labs when lab.id == 0. Did you mean to updateLab()?");

        try(PreparedStatement preparedStatement = CONNECTION.prepareStatement("INSERT INTO labs (name) VALUES (?)")) {
            preparedStatement.setString(1, lab.getName());
            return preparedStatement.executeUpdate() > 0;
        }
    }

    /**
     * Updates a Lab in the database who's id matches the lab parameter's id
     * @param lab The Lab being updated
     * @return True if updated successfully false if not.
     * @throws SQLException Thrown if something goes wrong with the database and connection
     */
    public Optional<Lab> updateLab(Lab lab) throws SQLException {
        if (lab == null)
            throw new IllegalArgumentException(LAB_NULL_MESSAGE);
        else if (lab.getLabId() <= 0)
            throw new IllegalArgumentException("Can only update labs when lab.id > 0. Did you mean to saveLab()?");

        try(PreparedStatement preparedStatement = CONNECTION.prepareStatement("UPDATE labs SET name=? WHERE lab_id=?")) {
            preparedStatement.setString(1, lab.getName());
            preparedStatement.setInt(2, lab.getLabId());
            if(preparedStatement.executeUpdate() > 0){
                return Optional.of(lab);
            }
        }
        return Optional.empty();
    }

    /**
     * Gets all Labs
     * @return A list of all Labs
     * @throws SQLException Thrown if something goes wrong with the database and connection
     */
    public List<Lab> getAll() throws SQLException {
        List<Lab> labs = new ArrayList<>();

        try(PreparedStatement preparedStatement = CONNECTION.prepareStatement("SELECT * FROM labs")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                labs.add(new Lab(resultSet.getInt(1), resultSet.getString(2)));
            }
        }
        return labs;
    }

    /**
     * Retrieves a Lab by that Lab's id field
     * @param id The id of the Lab being retrieved from the database
     * @return An Optional containing the requested lab if found, and an empty Optional if not found in the database
     * @throws SQLException Thrown if something goes wrong with the database and connection
     */
    public Optional<Lab> getLabById(int id) throws SQLException {
        if (id <= 0)
            throw new IllegalArgumentException("Can only get labs with id > 0");

        try(PreparedStatement preparedStatement = CONNECTION.prepareStatement("SELECT * FROM labs WHERE lab_id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return Optional.of(
                        new Lab(resultSet.getInt(1),
                                resultSet.getString(2))
                );

            } else {
                return Optional.empty();
            }
        }
    }

    /**
     * Deletes a Lab from the database
     * @param lab The Lab to be deleted
     */
    public void deleteLab(Lab lab){
        if (lab == null || lab.getLabId() <= 0)
            return;

        try(PreparedStatement preparedStatement = CONNECTION.prepareStatement("DELETE FROM labs WHERE lab_id=?")) {
            preparedStatement.setInt(1, lab.getLabId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a Lab from the database
     * @param labId The ID of the Lab to be deleted
     */
    public void deleteLabById(int labId){
        if (labId <= 0)
            return;

        try(PreparedStatement preparedStatement = CONNECTION.prepareStatement("DELETE FROM labs WHERE lab_id=?")) {
            preparedStatement.setInt(1, labId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
