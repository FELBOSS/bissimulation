package org.usp.sfv.reader;

import org.usp.sfv.domain.Process;
import org.usp.sfv.domain.Relationship;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.stream.Stream;

/**
 * App: bissimulation User: caiobos Date: 9/11/15
 */
public class LTSReader {

    private static final String SEPARATOR = "[\\s]+";
    private static final Integer STATE_FROM = 0;
    private static final Integer STATE_TO = 2;
    private static final Integer EVENT = 1;

    public Process read(String fileName) throws URISyntaxException {
        URI file = ClassLoader.getSystemResource(fileName).toURI();
        Path path = Paths.get(file);
        try {
            Stream<String> lines = Files.lines(path);
            Process process = new Process();
            lines.forEach(s -> process.addRelationship(readLine(s)));
            System.out.println(process);
        }
        catch (IOException ex) {
        }
        return new Process();
    }

    private Relationship readLine(String line) {
        String[] pieces = line.split(SEPARATOR);
        if (pieces.length != 3) {
            throw new IllegalArgumentException("LTS Format error at line=" + line);
        }
        Relationship r = new Relationship(pieces[STATE_FROM], pieces[STATE_TO], pieces[EVENT]);
        System.out.println(r);
        return r;
    }
}