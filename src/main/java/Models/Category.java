package Models;

public class Category {
    private long id;
    private String nameCata;
    private String description;
    private static long countIdCata = 1000L;

    public Category(String nameCata, String description) {
        this.id = countIdCata;
        this.nameCata = nameCata;
        this.description = description;
        countIdCata++;
    }

    public long getId() {
        return id;
    }
    public void setId(long id){
        this.id = id;
    }
    public String getNameCata() {
        return nameCata;
    }

    public void setNameCata(String nameCata) {
        this.nameCata = nameCata;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "nameCata='" + nameCata + '\'' +
                ", id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
