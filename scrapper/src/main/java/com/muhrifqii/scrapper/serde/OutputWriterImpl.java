package com.muhrifqii.scrapper.serde;

import java.io.IOException;
import java.nio.file.Files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OutputWriterImpl implements OutputWriter {

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    @Override
    public <T> void dumpToJson(T obj, String dirPath, String fileName) throws IOException {
        final var filePath = writeFileAndDir(dirPath, fileName);
        if (Files.exists(filePath)) {
            final var existing = Files.readString(filePath);
            final var latest = gson.toJson(obj);
            if (!existing.equals(latest)) {
                Files.writeString(filePath, latest);
            }
        } else {
            try (final var writer = Files.newBufferedWriter(filePath)) {
                gson.toJson(obj, writer);
            }
        }
    }
}
