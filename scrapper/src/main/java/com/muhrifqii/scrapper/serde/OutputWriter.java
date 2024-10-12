package com.muhrifqii.scrapper.serde;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public interface OutputWriter {
    <T> void dumpToJson(T obj, String dirPath, String fileName) throws IOException;

    default Path writeFileAndDir(String dirPath, String fileName) throws IOException {
        return Files
                .createDirectories(Paths.get(dirPath))
                .resolve(fileName);
    }
}
