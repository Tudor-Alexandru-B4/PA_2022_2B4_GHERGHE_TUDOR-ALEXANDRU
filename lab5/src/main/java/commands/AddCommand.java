package commands;

import model.Item;
import model.Catalog;

public class AddCommand implements Command{
    private Item item;

    public void command(Catalog catalog, Item item){
        this.item = item;
        command(catalog);
    }

    @Override
    public void command(Catalog catalog) {
        catalog.getItemCatalog().add(item);
    }
}
