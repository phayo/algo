import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

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

    public static void dfs(File file, Set<String> seen){
        if(file.isFile()){
            if(!seen.contains(file.getName())){
                seen.add(file.getName());
            }else {
                delete(file);
            }
            return;
        }

        List<File> content = getFolderContents(file);

        if(content.isEmpty()){
            delete(file);
            return;
        }

        PriorityQueue<String> pq = new PriorityQueue<>();

        for(File child : getFolderContents(file)){
            dfs(child, seen);
            pq.add(child.getName());
        }
        String folderString = hashFolder(String.join(",", pq));

        if(getFolderContents(file).isEmpty() || seen.contains(folderString)){
            delete(file);
            return;
        }

        seen.add(folderString);
    }
}
