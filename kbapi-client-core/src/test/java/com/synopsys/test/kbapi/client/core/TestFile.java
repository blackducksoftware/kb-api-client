/*
 * Copyright (C) 2022 Synopsys Inc.
 * http://www.synopsys.com/
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Synopsys ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Synopsys.
 */
package com.synopsys.test.kbapi.client.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Represents a file available for use in tests
 *
 * @author romeara
 * @since 0.1.0
 */
public final class TestFile {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    private static final Path RESOURCES_MODEL_JSON_PATH = Paths.get("com", "synopsys", "test", "kbapi", "client", "core", "model", "json");

    public static final TestFile DEPRECTATION_JSON = new TestFile(RESOURCES_MODEL_JSON_PATH.resolve("deprecation.json"));

    public static final TestFile FILTER_META_DATA_JSON = new TestFile(RESOURCES_MODEL_JSON_PATH.resolve("filterMetaData.json"));

    public static final TestFile LINK_JSON = new TestFile(RESOURCES_MODEL_JSON_PATH.resolve("link.json"));

    public static final TestFile META_DATA_JSON = new TestFile(RESOURCES_MODEL_JSON_PATH.resolve("metaData.json"));

    public static final TestFile META_DATA_MINIMUM_JSON = new TestFile(RESOURCES_MODEL_JSON_PATH.resolve("metaDataMinimum.json"));

    public static final TestFile PAGE_RESPONSE_JSON = new TestFile(RESOURCES_MODEL_JSON_PATH.resolve("pageResponse.json"));

    private final Path pathRelativeToSourceDir;

    private TestFile(Path pathRelativeToSourceDir) {
        this.pathRelativeToSourceDir = Objects.requireNonNull(pathRelativeToSourceDir);
    }

    /**
     * Reads the contents of the file to a string
     *
     * @return String representation of the file
     * @throws IOException
     *             If there was an error reading the file
     * @since 0.1.0
     */
    public String readToString() throws IOException {
        return readToString(pathRelativeToSourceDir);
    }

    /**
     * Reads from JSON to a specific Java representation using Jackson DataBind.
     *
     * <p>
     * The specific method of reading is noted due to differences in parsing which tests may wish to account for
     *
     * @param <T>
     *            The type to deserialize into
     * @param clazz
     *            Class value of the type to deserialize into
     * @return The deserialized value
     * @throws IOException
     *             If there was an error reading or deserializing the file
     * @since 0.1.0
     */
    public <T> T readToJsonDatabind(Class<T> clazz) throws IOException {
        Objects.requireNonNull(clazz);

        try (BufferedReader reader = forResource(pathRelativeToSourceDir)) {
            return JSON_MAPPER.readerFor(clazz).readValue(reader);
        }
    }

    /**
     * Reads the contents of a file from the class path into a string representation
     *
     * @param relativeToSourceDir
     *            Path relative to a source directory to read a file from on the class path
     * @return A string containing the contents of the provided file
     * @throws IOException
     *             If there is an error reading the file
     */
    private static String readToString(Path relativeToSourceDir) throws IOException {
        Objects.requireNonNull(relativeToSourceDir);
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = forResource(relativeToSourceDir)) {
            String line = reader.readLine();

            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        }

        return lines.stream().collect(Collectors.joining("\n"));
    }

    /**
     * @param relativeToSourceDir
     *            Path relative to a source directory to read a file from on the class path
     * @return A reader instance referencing the provided file
     */
    private static BufferedReader forResource(Path relativeToSourceDir) {
        Objects.requireNonNull(relativeToSourceDir);
        InputStream stream = TestFile.class.getClassLoader().getResourceAsStream(relativeToSourceDir.toString());

        return new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
    }

}
