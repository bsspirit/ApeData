package org.ape.data.core.util;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * String Utility Tools
 * 
 * @author Conan_Z
 * @data 2013-1-23
 */
public class FileUtil {

    public static String getFileExt(String filePath) {
        return filePath.substring(filePath.lastIndexOf(".") + 1);
    }

    public static String getFileName(String filePath) {
        filePath.replaceAll("\\\\$", File.separator);
        filePath.replaceAll("/$", File.separator);
        return filePath.substring(filePath.lastIndexOf(File.separator) + 1);
    }

    public static boolean isASCIIFile(String fileExt) {
        Set<String> set = new HashSet<String>();
        set.add("csv");
        set.add("txt");
        return set.contains(fileExt);
    }

}
