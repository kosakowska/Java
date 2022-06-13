package pl.GosiaKosakowska;

import pl.GosiaKosakowska.drive.Drive;
import pl.GosiaKosakowska.drive.HDDDrive;
import pl.GosiaKosakowska.file.File;
import pl.GosiaKosakowska.file.imagefile.GIFImageFile;
import pl.GosiaKosakowska.file.imagefile.JPGImageFile;
import pl.GosiaKosakowska.file.musicfile.MP3MusicFile;

public class Main {
    public static void main(String[] args)
    {
        GIFImageFile gif1 = new GIFImageFile("nazwa1.gif", 100);
        JPGImageFile jpg1 = new JPGImageFile("nazwa1.jpg", 200, 80);

        MP3MusicFile mp3file = new MP3MusicFile("plik.mp3", 4000, "Ed Sheeran", "Perfect", 100);

        Drive drive = new HDDDrive();
        drive.addFile(gif1);
        drive.addFile(jpg1);
        drive.addFile(mp3file);

        //drive.listFiles();
        File file = drive.findFile("plik.mp3");
        System.out.println(file.getSize());
    }
}
