package commands;

import exceptions.InvalidCatalogException;
import model.Catalog;
import exceptions.InvalidLocationException;
import exceptions.InvalidReportException;

public interface Command{
    void command(Catalog catalog) throws InvalidCatalogException, InvalidLocationException, InvalidReportException;
}
