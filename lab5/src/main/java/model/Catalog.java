package model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

import static java.lang.System.*;

public class Catalog{
    private List<Item> itemCatalog = new ArrayList<>();

    public void add(Item item){
        itemCatalog.add(item);
    }

    public void load(String path){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            itemCatalog = objectMapper.readValue(new File(path), new TypeReference<List<Item>>(){});
        }catch (Exception e){
            out.println("DID NOT LOAD");
            out.println(e);
        }
    }

    public void setItemCatalog(List<Item> itemCatalog){
        this.itemCatalog = itemCatalog;
    }
    public List<Item> getItemCatalog(){
        return itemCatalog;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "catalog=" + itemCatalog +
                "\n}";
    }
}

