package com.example.springrecipeapp.services;

import com.example.springrecipeapp.models.Recipe;
import com.example.springrecipeapp.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long id, MultipartFile file) {

        try {
            Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new RuntimeException("recipe not found"));
            Byte[] image = new Byte[file.getBytes().length];
            int index = 0;
            for (byte b : file.getBytes()) {
                image[index++] = b;
            }

            recipe.setImage(image);
            recipeRepository.save(recipe);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
