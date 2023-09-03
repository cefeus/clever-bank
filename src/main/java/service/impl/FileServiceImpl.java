package service.impl;

import service.FileService;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
/**
 * Класс для формирования txt файла
 */
public class FileServiceImpl implements FileService {

    /**
     * метод записи в txt файл
     * @param path - путь до папки содержащей txt файлы
     * @param text - содержимое для записи в файл
     */
    public void formTxt(String path, String text) {
        try (var fWriter = new FileWriter(path, Charset.forName("WINDOWS-1251"))) {
            fWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
