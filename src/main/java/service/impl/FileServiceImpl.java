package service.impl;

import service.FileService;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileServiceImpl implements FileService {

    public void formTxt(String path, String text) {
        try (var fWriter = new FileWriter(path, Charset.forName("WINDOWS-1251"))) {
            fWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
