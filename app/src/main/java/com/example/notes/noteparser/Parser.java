package com.example.notes.noteparser;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
    public String fileName(File file){
        return file.getName();
    }
    public String parseFile(File file) { //собирает строки в StringBuilder
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Обрабатываем каждую строку, например
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            Log.e("FileParser", "Ошибка чтения файла", e);
        }
        return sb.toString();
    }

    public int countLines(File file) { //подсчет строк в файле
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            Log.e("FileParser", "Ошибка чтения файла", e);
        }
        return count;
    }
}
