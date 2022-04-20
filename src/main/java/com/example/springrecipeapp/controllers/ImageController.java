package com.example.springrecipeapp.controllers;

import com.example.springrecipeapp.commands.RecipeCommand;
import com.example.springrecipeapp.services.ImageService;
import com.example.springrecipeapp.services.RecipeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {

    private final RecipeService recipeService;
    private final ImageService imageService;

    public ImageController(RecipeService recipeService, ImageService imageService) {
        this.recipeService = recipeService;
        this.imageService = imageService;
    }


    @GetMapping("/recipe/{recipeId}/image")
    public String initUploadForm(Model model, @PathVariable Long recipeId) {

        model.addAttribute("recipe", recipeService.findCommandById(recipeId));
        return "/recipe/imageUploadForm";
    }


    @PostMapping("/recipe/{recipeId}/image")
    public String processUploadForm(@PathVariable Long recipeId, @RequestParam("imagefile")MultipartFile file) {

        imageService.saveImageFile(recipeId, file);
        return "redirect:/recipe/show/" + recipeId;
    }


    @GetMapping("/recipe/{recipeId}/recipeimage")
    public void renderImageFromDB(@PathVariable Long recipeId, HttpServletResponse response) throws IOException {

        RecipeCommand recipeCommand = recipeService.findCommandById(recipeId);
        if (recipeCommand.getImage() != null) {
            byte[] image = new byte[recipeCommand.getImage().length];

            int index = 0;
            for(Byte b : recipeCommand.getImage()) {
                image[index++] = b;
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(image);
            IOUtils.copy(is, response.getOutputStream());

        }
    }
}
