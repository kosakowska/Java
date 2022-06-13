package pl.GosiaKosakowska.drive;

import pl.GosiaKosakowska.file.File;

public interface Drive {
    void addFile(File file);
    void listFiles();
    File findFile(String name);
}
