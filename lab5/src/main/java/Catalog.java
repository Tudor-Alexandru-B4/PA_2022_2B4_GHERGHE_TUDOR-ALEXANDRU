import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

import static java.lang.System.out;

public class Catalog{
    private List<Item> itemCatalog = new ArrayList<>();

    public void add(Item item){
        itemCatalog.add(item);
    }

    public void save(String path){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try{
            objectMapper.writeValue(new File(path), itemCatalog);
        }catch (Exception e){
            out.println("DID NOT SAVE");
            out.println(e);
        }
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

    @Override
    public String toString() {
        return "Catalog{" +
                "catalog=" + itemCatalog +
                '}';
    }
}

