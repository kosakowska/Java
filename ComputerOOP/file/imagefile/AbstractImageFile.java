package pl.GosiaKosakowska.file.imagefile;

import pl.GosiaKosakowska.file.AbstractFile;
import pl.GosiaKosakowska.file.FileType;

public abstract class AbstractImageFile extends AbstractFile {


    protected AbstractImageFile(String name, int size) {
        super(name, size);
    }


    @Override
    public FileType getType() {
        return FileType.IMAGE;
    }
}
