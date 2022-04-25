package goit.entity;



import java.util.ArrayList;
import java.util.List;

public class Pet {
    private long id;
    private Category category;
    private String name;
    private List<String> photoUrls = new ArrayList<>();
    private Tag tag;
    private String status;

    public Pet() {
        id = 100000;
        category = new Category(1, "Cat");
        name = "Murchik";
        photoUrls.add("https://site.com");
        tag = new Tag(1, "cat");
        status = Statuses.AVAILABLE.toString();
    }

    public Pet(Category category, String name, List<String> photoUrls, Tag tag, String status) {
        this.id = 1000000;
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tag = tag;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tags) {
        this.tag = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", photoUrls=" + photoUrls +
                ", tag=" + tag +
                ", status=" + status +
                '}';
    }
}
