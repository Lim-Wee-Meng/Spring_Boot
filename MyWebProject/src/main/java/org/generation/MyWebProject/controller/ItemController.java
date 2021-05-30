package org.generation.MyWebProject.controller;

import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.generation.MyWebProject.component.FileUploadUtil;
import org.generation.MyWebProject.component.FileUploadUtil;
import org.generation.MyWebProject.controller.dto.ItemDTO;
import org.generation.MyWebProject.repository.Entity.Item;
import org.generation.MyWebProject.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.generation.MyWebProject.repository.ItemRepository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.*;

import java.io.IOException;

@RestController
@RequestMapping("/item")
public class ItemController {
    //proper way - Accessing ItemRepository via service layers
    final ItemService itemService;

    public ItemController(@Autowired ItemService itemService){
        this.itemService = itemService;
    }

    @CrossOrigin // to avoid CORS
    @GetMapping ("/all")
    public Iterable<Item> getItems() {
        return itemService.all();
    }

    @CrossOrigin
    @PostMapping("/add")
    public Item save( @RequestParam(name="name",required= true)String name ,
                      @RequestParam(name="description",required= true)String description,
                      @RequestParam(name="imageUrl",required= true)String imageUrl,
                      @RequestParam(name="style",required= true)String style,
                      @RequestParam(name="price",required= true) double price,
                      @RequestParam("imagefile") MultipartFile multipartFile) throws IOException {
        String uploadDir1="productImages/images";
        //String uploadDir2="build/resources/main/static/images"

        System.out.println("Inside");

        System.out.println("aaaa : " + multipartFile.getOriginalFilename());

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        FileUploadUtil.saveFile(uploadDir1,fileName, multipartFile);

        Item itemDTO = new Item(name, description, imageUrl, style, price);
        return itemService.save(itemDTO);
    }


    //passing the ItemDTO class object into the save method
    //ItemDTO class object is the object that received the data passed from fetch/Ajax method in our frontend js
    //
//    @CrossOrigin
//    @PostMapping("/add")
//    public Item save(@RequestBody ItemDTO itemDTO){
//        System.out.println("In Save");
//        return itemService.save(new Item(itemDTO));
//    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Item findItemById (@PathVariable Integer id){
        return itemService.findById(id);
    }

    //public Item update(@PathVariable Integer id)
    @CrossOrigin
    @PutMapping("/{id}")
    public Item update(@PathVariable Integer id, @RequestBody ItemDTO itemDTO){
        System.out.println("Hello");
        Item item=itemService.findById(id);
        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        item.setImageUrl(itemDTO.getImageUrl());
        item.setStyle(itemDTO.getStyle());
        item.setPrice(itemDTO.getPrice());
        return itemService.save(item);

    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        itemService.delete(id);
    }


    //improper way - Direct accessing ItemRepository skipping service layers
    /*final ItemRepository itemRepository;

    public ItemController(@Autowired ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public Iterable<Item> getItems(){
        return itemRepository.findAll();
    }*/



}
