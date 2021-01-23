package pl.javastart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.entity.Category;
import pl.javastart.repository.CategoryRepository;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CategoryService {

    private final Scanner scanner;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(Scanner scanner, CategoryRepository categoryRepository) {
        this.scanner = scanner;
        this.categoryRepository = categoryRepository;
    }

    public void addCategory() {
        Category category = readCategory();
        categoryRepository.save(category);
        System.out.println("Dodano kategorię\n" + category);
    }

    private Category readCategory() {
        Category category = new Category();
        System.out.println("Podaj nazwę kategorii:");
        category.setName(scanner.nextLine());
        System.out.println("Podaj opis kategorii:");
        category.setDescription(scanner.nextLine());
        return category;
    }

    public void deleteCategory() {
        System.out.println("Podaj id kategorii, którą chcesz usunąć:");
        Long categoryId = scanner.nextLong();
        Optional<Category> category = categoryRepository.findById(categoryId);
        category.ifPresentOrElse(categoryRepository::delete, () -> System.out.println("Brak kategorii o wskazanym id"));
    }
}
