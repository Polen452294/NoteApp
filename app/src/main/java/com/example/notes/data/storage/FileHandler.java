package com.example.notes.data.storage;

import android.content.Context;
import com.example.notes.domain.model.Note;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class FileHandler {
    private final Context context;

    public FileHandler(Context context) {
        this.context = context;
    }

    // Метод для сохранения заметки в файл
    public boolean saveNoteAsTxt(Note note, String fileName) {
        try{
            File file = new File(context.getFilesDir(), fileName);
            FileWriter writer = new FileWriter(file);
            writer.write(note.getTitle() + "\n" + note.getContent());
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Метод для чтения содержимого файла
    public String readFromFile(File file) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    // Дополнительно: метод для удаления файла
    public boolean deleteFile(String fileName) {
        File file = new File(context.getFilesDir(), fileName);
        return file.delete();
    }
}
