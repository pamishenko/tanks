package edu.school21.utils;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")

public class Args {
    public Args() {
    }

    public String getServer() {
        return server;
    }

    public String getName() {
        return name;
    }

    @Parameter(names = {"--ip", "-ip"})
    private String server;

    public int getPort() {
        return port;
    }

    @Parameter(names = {"--port", "--p", "-p"})
    private int port;

    @Parameter(names = {"--name", "-name"})
    private String name;
}
