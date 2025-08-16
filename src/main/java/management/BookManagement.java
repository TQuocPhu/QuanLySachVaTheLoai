package management;

import Models.Book;
import dto.BookDatabase;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookManagement implements IManagement<Book> {
    List<Book> list = new ArrayList<>();
    private BookDatabase bookDatabase = new BookDatabase();

    public BookManagement(){
        this.list = bookDatabase.readData();
    }

    @Override
    public void add(Book book) {
        this.list.add(book);
        bookDatabase.writeData(this.list);
    }

    @Override
    public void delete(long id) throws Exception{
        int index = this.findIndexById(id);
        this.list.remove(index);
        bookDatabase.writeData(this.list);
    }

    @Override
    public void update(long id, Book newBook) throws Exception{
        int index = this.findIndexById(id);
        Book oldData = this.findById(id);
        newBook.setId(oldData.getId());
        this.list.set(index, newBook);
        bookDatabase.writeData(this.list);
    }

    @Override
    public Book findById(long id) throws Exception{
        int index = this.findIndexById(id);
        return this.list.get(index);
    }

    @Override
    public List<Book> findAll() {
        return this.list;
    }

    @Override
    public int findIndexById(long id) throws Exception{
        for(int i = 0; i < this.list.size(); i++){
            if(Objects.equals(this.list.get(i).getId(),id)){
                return i;
            }
        }
        throw new Exception("Data Not Found");
    }
}
