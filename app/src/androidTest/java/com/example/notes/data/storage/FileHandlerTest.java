package com.example.notes.data.storage;

import android.content.Context;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.notes.domain.model.Note;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class FileHandlerTest {
    private Context context;
    private FileHandler fileHandler;
    private String testFileName = "test_note.txt";

    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
        fileHandler = new FileHandler(context);
    }

    @Test
    public void testSaveAndReadNote() {
        Note testNote = new Note(1, "Test Title", "This is a test content", System.currentTimeMillis());

        boolean isSaved = fileHandler.saveNoteAsTxt(testNote, testFileName);
        assertTrue("Файл должен быть успешно сохранен", isSaved);

        File file = new File(context.getFilesDir(), testFileName);
        assertTrue("Файл должен существовать", file.exists());

        String content = fileHandler.readFromFile(file);
        assertEquals("Test Title\nThis is a test content\n", content);
    }

    @Test
    public void testDeleteFile() {
        File file = new File(context.getFilesDir(), testFileName);
        if (!file.exists()) {
            fileHandler.saveNoteAsTxt(new Note(1, "Temp", "To be deleted", System.currentTimeMillis()), testFileName);
        }

        boolean isDeleted = fileHandler.deleteFile(testFileName);
        assertTrue("Файл должен быть удален", isDeleted);
        assertFalse("Файл больше не должен существовать", file.exists());
    }

    @After
    public void tearDown() {
        File file = new File(context.getFilesDir(), testFileName);
        if (file.exists()) {
            file.delete();
        }
    }
}
