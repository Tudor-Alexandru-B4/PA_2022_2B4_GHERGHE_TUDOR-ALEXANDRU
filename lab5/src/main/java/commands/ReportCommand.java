package commands;

import model.Catalog;
import exceptions.InvalidReportException;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class ReportCommand implements Command{

    @Override
    public void command(Catalog catalog) throws InvalidReportException{
        try{
            Configuration configuration = new Configuration();
            configuration.setDirectoryForTemplateLoading(new File("src/main/resources/"));
            Template template = configuration.getTemplate("report.ftl");
            FileOutputStream fout = new FileOutputStream("target/report.html");
            Writer writer = new OutputStreamWriter(fout);
            template.process(catalog, writer);
            Desktop desktop = Desktop.getDesktop();
            desktop.open(new File("target/report.html"));
        }catch (Exception e){
            throw new InvalidReportException(e);
        }
    }
}
