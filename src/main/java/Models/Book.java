package Models;

public class Book {
    private long id;
    private String name;
    private String author;
    private static long countId = 1000L;

//    Luu tru id category
    private long categoryId;

    public Book(long id, String name, String author, long categoryId) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.categoryId = categoryId;
    }

    public Book(String name, String author, long categoryId) {
        this.id = countId;
        this.name = name;
        this.author = author;
        this.categoryId = categoryId;
        countId++;
    }

    public long getId() {
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Book: " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}
