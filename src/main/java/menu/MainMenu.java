package menu;

import Models.Book;
import Models.Category;
import Models.User;
import lib.Input;
import management.BookManagement;
import management.CategoryManagement;

import java.util.List;

public class MainMenu {
    private BookManagement bookManagement;
    private CategoryManagement categoryManagement;
    private User currentUser;

    public MainMenu(User currentUser) {
        this.bookManagement = new BookManagement();
        this.categoryManagement = new CategoryManagement();
        this.currentUser = currentUser;
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("====== Menu =======");
            System.out.println("1. Sach");
            System.out.println("2. Loai Sach");
            System.out.println("0. Thoat");

            System.out.println("Nhap lua chon cua ban");
            choice = Input.inputIntNumber();

            switch (choice) {
                case 1:
                    showBookMenu();
                    break;
                case 2:
                    showCategoryMenu();
                    break;
                default:
                    System.out.println("Khong co lua chon nay");
            }

        } while (choice != 0);
    }

    //   Category Feature
    public void showCategoryMenu() {
        int choice;
        do {
            System.out.println("====== Category Menu =======");
            System.out.println("1. Hien thi tat ca");

            if ("admin".equals(currentUser.getRole())) {
                System.out.println("2. Them loai");
                System.out.println("3. Sua loai");
                System.out.println("4. Xoa loai");
            }

            System.out.println("0. Thoat");

            System.out.println("Nhap lua chon cua ban");
            choice = Input.inputIntNumber();

            switch (choice) {
                case 1 -> showAllCategory();
                case 2 -> {
                    if ("admin".equals(currentUser.getRole())) showFormAddCategory();
                    else System.out.println("Ban khong co quyen!");
                }
                case 3 -> {
                    if ("admin".equals(currentUser.getRole())) showFormEditCategory();
                    else System.out.println("Ban khong co quyen!");
                }
                case 4 -> {
                    if ("admin".equals(currentUser.getRole())) showFormDeleteCategory();
                    else System.out.println("Ban khong co quyen!");
                }
                case 0 -> System.out.println("GoodBye!");
                default -> System.out.println("Khong co lua chon nay");
            }
        } while (choice != 0);
    }


    public void showAllCategory() {
        System.out.println("====== List Category =======");
        List<Category> categoryList = this.categoryManagement.findAll();

        for (Category category : categoryList) {
            System.out.println(category);
        }
    }


    public void showFormAddCategory() {
        System.out.println("====== Add Category =======");
        System.out.println("Nhap ten: ");
        String nameCategory = Input.inputString();
        System.out.println("Nhap mo ta");
        String desCategory = Input.inputString();

        Category newCategory = new Category(nameCategory, desCategory);
        this.categoryManagement.add(newCategory);
        System.out.println("Them loai thanh cong");
    }

    public void showFormEditCategory(){
        System.out.println("====== Edit Category =======");
        System.out.println("Vui long nhap id the loai can sua: ");
        long id = Input.inputLongNumber();

        try {
            Category oldCategory = categoryManagement.findById(id);
            System.out.println("Nhap ten moi (" + oldCategory.getNameCata() + "): ");
            String newNameCate = Input.inputString();

            System.out.println("Nhap mo ta moi: ");
            String newDescription = Input.inputString();

            Category newCategory = new Category(newNameCate, newDescription);
            categoryManagement.update(id, newCategory);
            System.out.println("Sua thanh cong!");
        } catch (Exception e){
            System.out.println("Khong tim thay the loai sach nay! ");
        }
    }

    public void showFormDeleteCategory(){
        System.out.println("====== Delete Category =======");
        System.out.println("Vui long nhap id the loai can xoa: ");
        long id = Input.inputLongNumber();

        try{
            categoryManagement.delete(id);
            System.out.println("Xoa thanh cong !");
        } catch (Exception e){
            System.out.println("Khong tim thay the loai sach nay! ");
        }
    }

    //    Book Feature
    public void showBookMenu() {
        int choice;
        do {
            System.out.println("====== Book Menu =======");
            System.out.println("1. Hien thi tat ca");
            System.out.println("2. Chi tiet sach");

            if ("admin".equals(currentUser.getRole())) {
                System.out.println("3. Them sach");
                System.out.println("4. Sua sach");
                System.out.println("5. Xoa sach");
            }

            System.out.println("0. Thoat");

            choice = Input.inputIntNumber();

            switch (choice) {
                case 1 -> showAllBook();
                case 2 -> showBookDetail();
                case 3 -> {
                    if ("admin".equals(currentUser.getRole())) showFormAddBook();
                    else System.out.println("Ban khong co quyen!");
                }
                case 4 -> {
                    if ("admin".equals(currentUser.getRole())) showFormEditBook();
                    else System.out.println("Ban khong co quyen!");
                }
                case 5 -> {
                    if ("admin".equals(currentUser.getRole())) showFormDeleteBook();
                    else System.out.println("Ban khong co quyen!");
                }
                case 0 -> System.out.println("GoodBye!");
                default -> System.out.println("Khong co lua chon nay");
            }
        } while (choice != 0);
    }


    public void showAllBook(){
        System.out.println("====== List Book =======");
        List<Book> bookList = this.bookManagement.findAll();

        for(Book book : bookList){
            Category category = getCategoryById(book.getCategoryId());
            String categoryName = "";
            if(category != null){
                categoryName = category.getNameCata();
            }
            System.out.println("Id: " + book.getId()
                                + " name: " + book.getName()
                                + " tac gia: " + book.getAuthor()
                                + " the loai: " + categoryName);
        }
    }

    private Category getCategoryById(long id){
        try{
            return this.categoryManagement.findById(id);
        }catch (Exception e){
            return null;
        }
    }

    public void showFormAddBook(){
        System.out.println("====== Add Book =======");
        System.out.println("Nhap ten: ");
        String nameBook = Input.inputString();
        System.out.println("Nhap tac gia");
        String authorBook = Input.inputString();
//        show list option for category
        List<Category> categoryList = categoryManagement.findAll();
        System.out.println("Danh sach the loai: ");
        if (categoryList.isEmpty()) {
            System.out.println("Chua co the loai nao. Vui long them truoc khi them sach.");
            return;
        }
        for(Category category : categoryList){
            System.out.println(category);
        }
        System.out.println("Vui long nhap id loai sach");
        long categoryId = (long) Input.inputIntNumber();
//        Kiem tra xem categoryId nguoi dung nhap vao co ton tai hay khong
        try {
            Book book = new Book(nameBook, authorBook, categoryId);
            bookManagement.add(book);
            System.out.println("Them sach thanh cong");
        } catch (Exception e){
            System.out.println("Khong tim thay the loai voi id = " + categoryId);
        }
    }

    public void showBookDetail(){
        System.out.println("====== Book Detail =======");
        System.out.println("Vui long nhap id sach can xem chi tiet: ");
        long id = Input.inputIntNumber();

        try{
            Book book = bookManagement.findById(id);
            Category category = getCategoryById(book.getCategoryId());
            String categoryName = (category != null) ? category.getNameCata() : "Khong xac dinh";
            String categoryDescription = (category != null) ? category.getDescription() : "Khong co mo ta";
            System.out.println("===== Chi tiet sach =====");
            System.out.println("ID: " + book.getId());
            System.out.println("Ten: " + book.getName());
            System.out.println("Tac gia: " + book.getAuthor());
            System.out.println("The loai: " + categoryName);
            System.out.println("Mo ta the loai: " + categoryDescription);
        } catch (Exception e){
            System.out.println("Khong tim thay sach voi id = " + id);
        }
    }

    public void showFormDeleteBook(){
        System.out.println("====== Delete Book =======");
        System.out.println("Vui long nhap id sach can xoat: ");
        long id = Input.inputIntNumber();
        try{
            bookManagement.delete(id);
            System.out.println("Xoa thanh cong");
        }catch (Exception e){
            System.out.println("Khong tim thay sach voi id = " + id);
        }
    }

    public void showFormEditBook(){
        System.out.println("====== Edit Book =======");
        System.out.println("Vui long nhap id sach can sua: ");
        long id = Input.inputIntNumber();
        try {
            Book oldBook = bookManagement.findById(id);
            System.out.println("Nhap ten moi (" +oldBook.getName() + "): ");
            String newBookName = Input.inputString();
            System.out.println("Nhap tac gia (" +oldBook.getAuthor() + "): ");
            String newBookAuthor = Input.inputString();

            List<Category> categoryList = categoryManagement.findAll();
            System.out.println("Danh sach the loai: ");
            if (categoryList.isEmpty()) {
                System.out.println("Chua co the loai nao. Vui long them truoc khi them sach.");
                return;
            }
            for(Category category : categoryList){
                System.out.println(category);
            }
            System.out.print("Nhap id the loai moi (" + oldBook.getCategoryId() + "): ");
            long newCategoryId = Input.inputLongNumber();

            Book newBook = new Book(newBookName, newBookAuthor, newCategoryId);
            bookManagement.update(id, newBook);
            System.out.println("Sua thanh cong !");
        } catch (Exception e){
            System.out.println("Khong tim thay sach voi id = " + id);
        }
    }
}
