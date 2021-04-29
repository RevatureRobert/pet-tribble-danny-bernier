package dev.tribble.database;

import dev.tribble.model.Tribble;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TribbleDAO {
    private final Connection CONNECTION;
    private static final String TRIBBLE_NULL_MESSAGE = "Parameter tribble cannot be null.";

    public TribbleDAO(){
        this.CONNECTION = DBConnection.INSTANCE.getConnection();
    }

    /**
     * Saves a new Tribble to the database
     * @param tribble The Tribble to be saved
     * @return Returns true if saved successfully false if not
     * @throws SQLException Thrown if something goes wrong with the database and connection
     */
    public boolean saveTribble(Tribble tribble) throws SQLException {

        if(tribble == null)
            throw new IllegalArgumentException(TRIBBLE_NULL_MESSAGE);

        else if (tribble.getId() != 0)
            throw new IllegalArgumentException("Can only save new tribbles when tribble.id == 0. Did you mean to updateTribble()?");

        try(PreparedStatement preparedStatement = CONNECTION.prepareStatement("INSERT INTO tribbles (name, age, lab_id) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, tribble.getName());
            preparedStatement.setInt(2, tribble.getAge());
            preparedStatement.setInt(3, tribble.getLabId());
            return preparedStatement.executeUpdate() > 0;
        }
    }

    /**
     * Updates a Tribble in the database
     * @param tribble The Tribble to be updated
     * @return Returns true if updated successfully false if not
     * @throws SQLException Thrown if something goes wrong with the database and connection
     */
    public boolean updateTribble (Tribble tribble) throws SQLException {
        if(tribble == null)
            throw new IllegalArgumentException(TRIBBLE_NULL_MESSAGE);

        else if (tribble.getId() <= 0)
            throw new IllegalArgumentException("Can only update tribbles when tribble.id > 0. Did you mean to saveTribble()?");

        try(PreparedStatement preparedStatement = CONNECTION.prepareStatement("UPDATE tribbles SET name=?, age=?, lab_id=? WHERE tribble_id=?")) {
            preparedStatement.setString(1, tribble.getName());
            preparedStatement.setInt(2, tribble.getAge());
            preparedStatement.setInt(3, tribble.getLabId());
            preparedStatement.setInt(4, tribble.getId());
            return preparedStatement.executeUpdate() > 0;
        }
    }

    /**
     * Retrieves a Tribble from the database who's id matches the parameter tribbleId
     * @param tribbleId The id of the Tribble being retrieved
     * @return An Optional containing the requested Tribble if found, and an empty Optional if not found in the database
     * @throws SQLException Thrown if something goes wrong with the database and connection
     */
    public Optional<Tribble> getTribbleById(int tribbleId) throws SQLException {
        if (tribbleId <= 0)
            throw new IllegalArgumentException("Can only get tribbles with id > 0");

        try(PreparedStatement preparedStatement = CONNECTION.prepareStatement("SELECT * FROM tribbles WHERE tribble_id=?")) {
            preparedStatement.setInt(1, tribbleId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return Optional.of(
                        new Tribble(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4))
                );

            } else {
                return Optional.empty();
            }

        }
    }

    /**
     * Deletes a Tribble from the database
     * @param tribble The tribble to be deleted
     * @throws SQLException Thrown if something goes wrong with the database and connection
     */
    public void deleteTribble(Tribble tribble) throws SQLException {
        if(tribble == null || tribble.getId() <= 0)
            return;

        try(PreparedStatement preparedStatement = CONNECTION.prepareStatement("DELETE FROM tribbles WHERE tribble_id=?")) {
            preparedStatement.setInt(1, tribble.getId());
            preparedStatement.executeUpdate();
        }
    }
}
