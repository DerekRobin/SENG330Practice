import java.io.File;
import java.util.ArrayList;

public class Folder{

    private String folderName;
    private ArrayList<File> files;

    Folder(String name, ArrayList<File> files) {
        this.folderName = name;
        this.files = files;
    }

    public String toString(){
        return this.folderName;
    }
}