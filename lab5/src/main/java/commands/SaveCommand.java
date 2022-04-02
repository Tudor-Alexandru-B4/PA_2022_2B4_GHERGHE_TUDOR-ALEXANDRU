package commands;

import exceptions.InvalidCatalogException;
import model.Catalog;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;

public class SaveCommand implements Command{
    private String path;

    public void command(Catalog catalog, String path) throws InvalidCatalogException{
        this.path = path;
        command(catalog);
    }

    @Override
    public void command(Catalog catalog) throws InvalidCatalogException {
        if(path.isEmpty()){
            path = "target/catalog.json";
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try{
            objectMapper.writeValue(new File(path), catalog.getItemCatalog());
        }catch (Exception e){
            throw new InvalidCatalogException(e);
        }
    }
}
