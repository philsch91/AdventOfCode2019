package at.fhcampuswien.adventofcode;

import at.fhcampuswien.adventofcode.puzzle.Puzzle;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

public class App {

    private static List<Class> getClassesForPackage(String packageName) throws ClassNotFoundException {
        ArrayList<File> directories = new ArrayList<File>();
        try {
            ClassLoader cld = Thread.currentThread().getContextClassLoader();
            if (cld == null) {
                throw new ClassNotFoundException("Can't get class loader.");
            }
            String path = packageName.replace('.', '/');
            Enumeration<URL> resources = cld.getResources(path);
            while (resources.hasMoreElements()) {
                directories.add(new File(URLDecoder.decode(resources.nextElement().getPath(), "UTF-8")));
            }
        } catch (NullPointerException x) {
            throw new ClassNotFoundException(packageName + " does not appear to be a valid package (Null pointer exception)");
        } catch (UnsupportedEncodingException encex) {
            throw new ClassNotFoundException(packageName + " does not appear to be a valid package (Unsupported encoding)");
        } catch (IOException ioex) {
            throw new ClassNotFoundException("IOException was thrown when trying to get all resources for " + packageName);
        }

        ArrayList<Class> classes = new ArrayList<>();
        for (File directory : directories) {
            if (directory.exists()) {
                String[] files = directory.list();
                for (String file : files) {
                    if (file.endsWith(".class")) {
                        try {
                            classes.add(Class.forName(packageName + '.' + file.substring(0, file.length() - 6)));
                        } catch (NoClassDefFoundError e) {
                        }
                    }
                }
            } else {
                throw new ClassNotFoundException(packageName + " (" + directory.getPath() + ") does not appear to be a valid package");
            }
        }
        return classes;
    }

    public static List<Class> updateClassList(List<Class> classList, List<String> exceptionList){
        List<Class> newClassList = new ArrayList<>();
        for(Class c : classList){
            if(!exceptionList.contains(c.getSimpleName())){
                newClassList.add(c);
            }
        }
        return newClassList;
    }

    public static List<Class> sortClassList(List<Class> classList){
        List<String> classNameList = new ArrayList<>();
        for(Class c : classList){
            classNameList.add(c.getSimpleName());
        }

        Collections.sort(classNameList);

        List<Class> sortedClassList = new ArrayList<>();
        for(String className : classNameList){
            for(Class c : classList){
                if(className.equals(c.getSimpleName())){
                    sortedClassList.add(c);
                }
            }
        }

        return sortedClassList;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("--- AdventOfCode 2019 ---");

        List<String> exceptions = new ArrayList<>();
        exceptions.add("Puzzle");
        exceptions.add("Day3Part10Puzzle");

        try {
            List<Class> list = getClassesForPackage("at.fhcampuswien.adventofcode.puzzle");
            list = updateClassList(list, exceptions);
            list = sortClassList(list);

            for (Class c : list) {
                Class<?> classInstance = Class.forName(c.getName());
                //System.out.println(classInstance.getSimpleName());
                Puzzle puzzle = (Puzzle) classInstance.getDeclaredConstructor().newInstance();
                Object result = puzzle.solve();
                System.out.println(classInstance.getSimpleName() + " result: " + result.toString());
            }
        } catch (Exception e) {
            System.err.println("Problem occurred: " + e.getMessage());
        }
    }
}
