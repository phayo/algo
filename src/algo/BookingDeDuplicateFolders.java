package algo;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class BookingDeDuplicateFolders {
    /**
     * Go recursively in to the folders.
     * Maintain a set of seen files and hash of seen folders
     * Delete files/folders already seen
     *
     * Uses Tree on folders
     *
     */

    public static void main(String[] args) {
        String root  = "/Users/ebuka/Project/algo/root";
        Set<String> seen = new HashSet<>();
        dfs(new File(root), seen);
    }

    public static List<File> getFolderContents(File folder){
        return List.of(folder.listFiles());
    }

    public static String hashFolder(String folder){
        String algorithm = "SHA-256";// "MD5";
        try
        {
            MessageDigest msgDst = MessageDigest.getInstance(algorithm);
            byte[] msgArr = msgDst.digest(folder.getBytes());

            BigInteger bi = new BigInteger(1, msgArr);

            String hshtxt = bi.toString(16);

            while (hshtxt.length() < 32)
            {
                hshtxt = "0" + hshtxt;
            }
            return hshtxt;
        }
        catch (NoSuchAlgorithmException abc) {
            throw new RuntimeException(abc);
        }
    }

    public static void delete(File file){
        file.delete();
    }

    public static boolean isFolder(File file){
        return !file.isFile();
    }

    public static void dfs(File file, Set<String> seen){
        // if the object is a file perform the delete if we have seen it before, else leave it
        if(file.isFile()){
            if(!seen.contains(file.getName())){
                seen.add(file.getName());
            }else {
                delete(file);
            }
            return;
        }

        // if the object is a folder delete it if its empty
        List<File> content = getFolderContents(file);

        if(content.isEmpty()){
            delete(file);
            return;
        }

        // Add each child name in a sorted manner using PQ and recursively perform all operation
        PriorityQueue<String> pq = new PriorityQueue<>();
        for(File child : getFolderContents(file)){
            dfs(child, seen);
            pq.add(child.getName());
        }

        // hash the names of the child files
        String folderString = hashFolder(String.join(",", pq));

        // Delete if the folder contents we have seen before or if it is now empty(we have recursively check the children and may have deleted everything inside)
        if(getFolderContents(file).isEmpty() || seen.contains(folderString)){
            delete(file);
            return;
        }

        // if not we add the hash to the seen folders and continue
        seen.add(folderString);
    }

    private static String childrenNameHash(List<File> children){
        PriorityQueue<String> pq = new PriorityQueue<>();
        children.stream().map(File::getName).forEach(pq::add);
        // hash the names of the child files
        return hashFolder(String.join(",", pq));
    }

    public static void deDupWithStack(File file){
        // You can use LinkedList<File> fileStack = new LinkedList<>() however make sure you are calling the right methods in other to operate as a stack
        Stack<File> fileStack = new Stack<>();
        Set<String> seen = new HashSet<>();
        fileStack.add(file);

        while(!fileStack.empty()){
            File currFile = fileStack.pop();

            if(isFolder(currFile)){
                List<File> childrenFiles = getFolderContents(currFile);
                if(childrenFiles.isEmpty() || seen.contains(childrenNameHash(childrenFiles))){
                    delete(currFile);
                } else {
                    // add the current folder back to the stack for treatment later
                    fileStack.push(currFile);
                    // add all child to the stack and aggregator all child names for storage in seen
                    childrenFiles.forEach(fileStack::push);
                }
            }
        }

    }
}
