package commands;

import exceptions.InvalidCatalogException;
import model.Catalog;
import model.Item;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

public class LoadCommand implements Command{
    private String path;

    public void command(Catalog catalog, String path) throws InvalidCatalogException{
        this.path = path;
        command(catalog);
    }

    @Override
    public void command(Catalog catalog) throws InvalidCatalogException {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            catalog.setItemCatalog(objectMapper.readValue(new File(path), new TypeReference<List<Item>>(){}));
        }catch (Exception e){
            throw new InvalidCatalogException(e);
        }
    }
}
