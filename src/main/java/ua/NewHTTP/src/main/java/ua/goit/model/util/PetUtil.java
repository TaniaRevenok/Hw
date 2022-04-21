package ua.goit.model.util;

import ua.goit.model.entity.Category;
import ua.goit.model.entity.Pet;
import ua.goit.model.entity.Tag;
import ua.goit.view.View;
import java.util.ArrayList;
import java.util.List;

public class PetUtil {
    public Pet createPetThrowConsole() {
        View view = new View();
        Pet pet = new Pet();
        view.write("Please enter the category:");
        Category category = new Category(1, view.read());
        pet.setCategory(category);
        view.write("Please provide photo's url:");
        List<String> urls = new ArrayList<>();
        urls.add(view.read());
        pet.setPhotoUrls(urls);
        view.write("Please enter tag:");
        Tag tag = new Tag(1,view.read());
        pet.setTag(tag);
        view.write("Enter status from: available pending, sold");
        pet.setStatus(view.read());
        return pet;
    }
}
