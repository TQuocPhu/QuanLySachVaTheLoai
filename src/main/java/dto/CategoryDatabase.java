package dto;
import Models.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDatabase {
    private File file;

    public CategoryDatabase(){
        this.file = new File("database/category.csv");
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

    public List<Category> readData(){
        try {
            FileReader fileReader = new FileReader(this.file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            List<Category> list = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null){
                String[] arr = line.split(",");
                long id = Long.parseLong(arr[0]);
                // Long id = Long.valueOf(arr[0]);
                String name = arr[1];
                String description = arr[2];
                Category category = new Category(id, name, description);
                list.add(category);
            }
            bufferedReader.close();
            return list;
        } catch (IOException e){
            System.out.println(e);
        }
        return null;
    }

    public void writeData(Category category) throws IOException {
        String oldListData = getStringData(); //lấy dữ liệu cũ từ file
        String newLine = category.getId() + "," + category.getNameCata() + "," + category.getDescription();
        String newListData = oldListData + newLine;
        FileWriter fileWriter = new FileWriter(this.file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(newListData); // ghi lại danh sách dữ liệu
        bufferedWriter.close();
    }

    public void writeData(List<Category> listCategory) {
        try {
            String newListData = "";
            for (Category category : listCategory) {
                newListData += category.getId() + "," + category.getNameCata() + "," + category.getDescription() + "\n";
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
