package commands;

import model.Catalog;
import exceptions.InvalidLocationException;

import java.awt.*;
import java.io.File;

public class ViewCommand implements Command {
    int index;

    public void command(Catalog catalog, int index) throws InvalidLocationException{
        this.index = index;
        command(catalog);
    }

    @Override
    public void command(Catalog catalog) throws InvalidLocationException {
        try{
            Desktop desktop = Desktop.getDesktop();
            desktop.open(new File(catalog.getItemCatalog().get(index).getLocation()));
        }catch (Exception e){
            throw new InvalidLocationException(e);
        }
    }
}
