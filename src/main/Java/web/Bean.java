package web;

import javax.ejb.Stateless;
import java.io.File;
import java.util.ArrayList;

/**
 * A class that implements Java Bean functionality.
 */
@Stateless(name = "MyEJBeanEJB")
public class Bean {
    /**
     * The method recursively goes deeper into the specified directory and returns a collection of files in it.
     *
     * @param startDir - Source directory
     * @return - ArrayList of the paths and file names contained in the specified directory.
     */
    public ArrayList<String> recursiveDirectory(String startDir) {
        File dir = new File(startDir);
        File[] files = dir.listFiles();
        ArrayList<String> recursiveList = new ArrayList<>();
        StringBuilder strBuff = new StringBuilder();


        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                    recursiveList.addAll(recursiveDirectory(file.getAbsolutePath()));
                } else {
                    strBuff.append(file.getAbsolutePath()).append(" -> ").append(file.getName());
                    recursiveList.add(strBuff.toString());
                    strBuff.setLength(0);
                }
            }
        }
        return recursiveList;
    }


    /**
     * The method replaces all the characters "/" with "\" in the input string to form the correct path
     * in the file system where app is running.
     *
     * @param inDirectory - The file system directory received with the GET request.
     * @return - Edited directory to search the file system.
     */
    public String checkFolderPath(String inDirectory) {
        String outDirectory = "";

        if ((inDirectory == null) || (inDirectory.isEmpty())) {
            outDirectory = ".";
        } else {
            if (isWindows()) {
                for (int i = 0; i < inDirectory.length(); i++) {
                    outDirectory = inDirectory.replace('/', '\\');
                }
            } else {
                outDirectory = inDirectory;
            }
        }
        return outDirectory;
    }

    private static boolean isWindows() {
        String os = System.getProperty("os.name").toLowerCase();
        return (os.contains("win"));
    }

}

