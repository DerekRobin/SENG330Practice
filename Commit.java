import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.*;
import java.io.File;


public class Commit implements Comparable<Commit> {


    private ArrayList<File> files;
    private ArrayList<Folder> folders;
    private Date commitDate;
    private String author;
    private String message;    
    private String commitID;

    Commit(ArrayList<File> files, ArrayList<Folder> folders, String author, String message){
        this.files = files;
        this.folders = folders;
        this.commitDate = new Date();
        this.author = author;
        this.message = message;
        this.commitID = generateCommitId();
    }

    private String generateCommitId(){
        String sha1 = "";
        try{
            String value = this.author + "_" + this.commitDate.toString();
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(value.getBytes("utf8"));
            sha1 = String.format("%040x", new BigInteger(1, digest.digest())); 
        } catch(Exception e) {
            e.printStackTrace();
        }
        return sha1;
    }

    public String toString(){
        return this.author + " " + this.commitID + " " + this.message;
    }

    public ArrayList<File> getFiles(){
        return new ArrayList<File>(this.files);
    }

    @Override
    public int compareTo(Commit that) {
        int compareValue = 0;
        ArrayList<File> thatFiles = that.getFiles();
        ArrayList<File> thisFiles = this.getFiles();
        Collections.sort(thatFiles);
        Collections.sort(thisFiles);
        Iterator<File> thatFilesIter = thatFiles.iterator();
        Iterator<File> thisFilesIter = thisFiles.iterator();
        if(thisFiles.size() > thatFiles.size()){
            compareValue = 1;
        } else if(thisFiles.size() < thatFiles.size()){
            compareValue = -1;
        } else {
            while(thatFilesIter.hasNext() && thisFilesIter.hasNext()){
                compareValue += thisFilesIter.next().compareTo(thatFilesIter.next());
            }
        }
        return compareValue;
    }

    public static void main(String[] args){
        //C:\Users\admin\Documents\SENG330
        File oneTwoThree = new File("C:\\Users\\admin\\Documents\\SENG330\\Midterm ex\\123.txt");
        File fourFiveSix = new File("C:\\Users\\admin\\Documents\\SENG330\\Midterm ex\\456.txt");
        ArrayList<File> files = new ArrayList<>();
        files.add(oneTwoThree);
        ArrayList<File> files2 = new ArrayList<>();
        files2.add(fourFiveSix);
        files2.add(fourFiveSix);
        Commit test = new Commit(files, new ArrayList<Folder>(), "Derek", "almost finished");
        Commit test2 = new Commit(files2, new ArrayList<Folder>(), "Matt", "almost finished omegalul");
        //System.out.println(oneTwoThree.compareTo(fourFiveSix));
        System.out.println(test.compareTo(test2));
    }
}