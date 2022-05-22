package other;

import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import static java.lang.System.out;

public class Main {
    public static void main(String args[]){
        String url = "Users/tudor/PA_2022_2B4_GHERGHE_TUDOR-ALEXANDRU/lab1/out/production/lab1/";
        String className = "Main";
        Class clazz = null;

        try {
            URL classUrl = new URL("file:///" + url);
            URL[] classUrls = {classUrl};
            URLClassLoader ucl = new URLClassLoader(classUrls);
            clazz = ucl.loadClass(className);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if(clazz == null){
            out.println("Class not found!");
            System.exit(0);
        }

        out.println("Package: " + getPackageName(clazz) + "\n");

        printMethods(clazz);

        callStaticMethods(clazz);

    }

    public static String getPackageName(Class c){
        String package_ = "";
        Package p = c.getPackage();
        if(p != null)
            package_ = p.getName();
        return package_;
    }

    public static void printMethods(Class c){
        Method[] methods = c.getMethods();

        for(int i = 0; i < methods.length; i++){
            out.print(methods[i].getReturnType() + " " + methods[i].getName() + "(");
            boolean first = true;
            for(var it : methods[i].getParameters()){
                if(first){
                    out.print(it.getType() + " " + it.getName());
                    first = false;
                }else{
                    out.print(", " + it.getType() + " " + it.getName());
                }
            }
            out.println(");\n");
        }
    }

    public static void callStaticMethods(Class c){
        Method[] methods = c.getMethods();
        for(int i = 0; i < methods.length; i++){
            if(methods[i].isAnnotationPresent(Test.class) && methods[i].getParameterCount() <=0){
                try {
                    methods[i].invoke(null);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
