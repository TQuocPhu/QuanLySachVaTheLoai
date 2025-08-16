package dto;

import Models.Book;
import Models.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookDatabase {
    private File file;

    public BookDatabase(){
        this.file = new File("database/book.csv");
    }

    public String getStringData() throws IOException {
        FileReader fileReader = new FileReader(this.file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        String output = "";
        while ((line = bufferedReader.readLine()) != null){
            output += line + "\n";
        }
        bufferedReader.close();
        return output;
    }

    public List<Book> readData(){
        try {
            FileReader fileReader = new FileReader(this.file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            List<Book> list = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null){
                String[] arr = line.split(",");
                long id = Long.parseLong(arr[0]);
                // Long id = Long.valueOf(arr[0]);
                String name = arr[1];
                String author = arr[2];
                long categoryId = Long.parseLong(arr[3]);
                Book book = new Book(id, name, author, categoryId);
                list.add(book);
            }
            bufferedReader.close();
            return list;
        } catch (IOException e){
            System.out.println(e);
        }
        return null;
    }

    public void writeData(Book book) throws IOException {
        String oldListData = getStringData(); //lấy dữ liệu cũ từ file
        String newLine = book.getId() + "," + book.getName() + "," + book.getAuthor() + "," + book.getCategoryId();
        String newListData = oldListData + newLine;
        FileWriter fileWriter = new FileWriter(this.file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(newListData); // ghi lại danh sách dữ liệu
        bufferedWriter.close();
    }

    public void writeData(List<Book> listBook) {
        try {
            String newListData = "";
            for (Book book : listBook) {
                newListData += book.getId() + "," + book.getName() + "," + book.getAuthor() + "," + book.getCategoryId() + "\n";
            }
            FileWriter fileWriter = new FileWriter(this.file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(newListData);
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
