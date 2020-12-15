package com.netcracker;

import com.netcracker.controllers.BookController;
import com.netcracker.controllers.DataBaseOracle;
import com.netcracker.models.services.BookManager;
import com.netcracker.models.types.Book;
import com.netcracker.views.BookView;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

//import java.sql.*;


public class Main {

        public static void main(String[] args)  {


            DataBaseOracle dataBaseOracle = new DataBaseOracle();
            BookManager container = new BookManager(dataBaseOracle);
            BookView view = new BookView();
            BookController controller = new BookController(container, view, dataBaseOracle);

            controller.doSmt();


        }

}


