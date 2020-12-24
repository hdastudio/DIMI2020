package com.netcracker.controllers.helpfun;

import com.netcracker.controllers.DataBaseOracle;
import com.netcracker.models.services.BookManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteFrom {

    public boolean delete(int index) {
        BookManager bookManager = BookManager.getStaticBookManager();
        DataBaseOracle dataBaseOracle = DataBaseOracle.getStaticDataBaseOracle();

        if (bookManager.checkIndex(index)) {

            int deleteByHash = bookManager.getElem(index).getHashBook();

            String querty = "delete from BOOKS\n" +
                    "where BOOK_ID = " + deleteByHash;
            Connection connection = dataBaseOracle.getConnection();
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(querty);
                bookManager.removeElem(index);
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        } else { // если нет такого
            return false;
        }
    }
}
