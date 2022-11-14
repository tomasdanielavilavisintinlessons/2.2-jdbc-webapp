package com.example.jdbcWebappExercise.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ResultSetUtility {
    public static ArrayList<String> toArrayList(ResultSet resultSet) throws SQLException  {
        ArrayList<String> result = new ArrayList<>();

        int columnCount = resultSet.getMetaData().getColumnCount();
        while (resultSet.next()) {
            String rowData = "";
            for(int i = 1; i <= columnCount; i++) {
                rowData = rowData + " " + resultSet.getString(i);
            }
            result.add(rowData);
        }

        return result;
    }

    public static int getFirstGeneratedKeyFrom(Statement statement) throws SQLException {
        int result = 0;

        ResultSet resultSet = statement.getGeneratedKeys();
        if(resultSet.first()) {
            result = resultSet.getInt(1);
        }

        return result;
    }
}
