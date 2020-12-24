package com.netcracker.controllers.helpfun;

import com.netcracker.controllers.DataBaseOracle;
import com.netcracker.models.services.BookManager;
import com.netcracker.models.types.Book;

public class CheckClone {
  public static boolean checkClone(Book book)
  {
      BookManager bookManager = BookManager.getStaticBookManager();

      bookManager.contains(book);



      return false;
  }
}
