package Adapter;

import Model.Book;

import java.util.List;

public interface DataSource {
    List<Book> getData();
}
