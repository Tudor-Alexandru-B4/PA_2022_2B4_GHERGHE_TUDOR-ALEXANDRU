package commands;

import model.Catalog;
import model.Item;

import static java.lang.System.*;

public class ListCommand implements Command{

    @Override
    public void command(Catalog catalog) {
        for(Item it : catalog.getItemCatalog()){
            out.println(it);
        }
    }
}
