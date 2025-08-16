package dto;

import Models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    private File file;

    public UserDatabase(String filename){
        this.file = new File(filename);
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

    public List<User> readData() {
        try{
            FileReader fileReader = new FileReader(this.file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;

            List<User> list = new ArrayList<>();
            while((line = bufferedReader.readLine()) != null) {
                String[] arr = line.split(",");
                long id = Long.parseLong(arr[0]);
                String username = arr[1];
                String password = arr[2];
                String fullname = arr[3];
                String role = arr[4];

                User user = new User(id, username, password, fullname, role);
                list.add(user);
            }
            bufferedReader.close();
            return list;
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public void writeData(User user) throws IOException {
        String oldListData = getStringData();
        String newLine = user.getId() + "," + user.getUsername() + "," + user.getPassword() + "," + user.getFullName() + "," + user.getRole();
        String newListData = oldListData + newLine;

        FileWriter fileWriter = new FileWriter(this.file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(newListData);
        bufferedWriter.close();
    }

    public void writeData(List<User> listUser){
        try{
            String newListData = "";
            for(User user : listUser){
                newListData += user.getId() + "," + user.getUsername() + "," + user.getPassword() + "," + user.getFullName() + "," + user.getRole();
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
