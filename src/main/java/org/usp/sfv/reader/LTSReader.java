package org.usp.sfv.reader;

import org.usp.sfv.domain.Process;
import org.usp.sfv.domain.Transition;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.stream.Stream;

/**
 * App: bissimulation
 * User: caiobos
 * Date: 9/11/15
 */
public class LTSReader {

    private static final String SEPARATOR = "[\\s]+";
    private static final Integer STATE_FROM = 0;
    private static final Integer STATE_TO = 2;
    private static final Integer EVENT = 1;

    private static LTSReader ourInstance = new LTSReader();

    public static LTSReader getInstance() {
        return ourInstance;
    }

    private LTSReader() {
    }

    public Process read(String fileName) throws URISyntaxException {
        URI file = ClassLoader.getSystemResource(fileName).toURI();
        Path path = Paths.get(file);
        try {
            Stream<String> lines = Files.lines(path);
            Process process = new Process();
            lines.forEach(s -> process.addTransition(readLine(s)));
            System.out.println("PROCESS="+process);
            return process;
        }
        catch (IOException ex) {
            System.out.println("Error when reading the file="+fileName);
        }
        return null;
    }

    private Transition readLine(String line) {
        String[] pieces = line.split(SEPARATOR);
        if (pieces.length != 3) {
            throw new IllegalArgumentException("LTS Format error at line=" + line);
        }
        Transition r = new Transition(pieces[STATE_FROM], pieces[EVENT], pieces[STATE_TO]);
        //System.out.println(r);
        return r;
    }
}