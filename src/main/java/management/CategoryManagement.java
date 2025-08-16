package management;

import Models.Category;
import dto.CategoryDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoryManagement implements IManagement<Category> {
    private CategoryDatabase categoryDatabase = new CategoryDatabase();
    List<Category> list = new ArrayList<>();

    public CategoryManagement(){
        this.list = categoryDatabase.readData();
    }

    @Override
    public void add(Category category) {
        this.list.add(category);
        categoryDatabase.writeData(this.list);
    }

    @Override
    public void delete(long id) throws Exception{
        int index = this.findIndexById(id);
        this.list.remove(index);
        categoryDatabase.writeData(this.list);
    }

    @Override
    public void update(long id, Category newCategory) throws Exception{
        int index = this.findIndexById(id);
        Category oldData = this.findById(id);
        newCategory.setId(oldData.getId());
        this.list.set(index, newCategory);
        categoryDatabase.writeData(this.list);
    }

    @Override
    public Category findById(long id) throws Exception{
        int index = this.findIndexById(id);
        return this.list.get(index);
    }

    @Override
    public List<Category> findAll() {
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
