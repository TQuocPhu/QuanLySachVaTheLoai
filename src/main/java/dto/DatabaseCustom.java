package dto;

import java.io.*;

public class DatabaseCustom {
    private File file;

    public DatabaseCustom(String filename){
        this.file = new File(filename);
    }

    public String readData() throws IOException {
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

    public void writeData(String newData) throws IOException {
        String oldListData = readData(); //lấy dữ liệu cũ từ file
        String newListData = oldListData + newData; //thêm dòng dữ liệu mới vào cuối dữ liệu cũ vào danh sách dữ liệu
        FileWriter fileWriter = new FileWriter(this.file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(newListData); // ghi lại danh sách dữ liệu
        bufferedWriter.close();
    }
}
