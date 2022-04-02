package commands;

import model.Catalog;

import static java.lang.System.*;

public class toStringCommand implements Command{

    @Override
    public void command(Catalog catalog) {
        out.println(catalog);
    }
}
