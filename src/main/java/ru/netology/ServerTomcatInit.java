package ru.netology;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import java.io.IOException;
import java.nio.file.Files;

public class ServerTomcatInit {
    private static final String BASE_DIRECTORY = "tomcat";
    private static final int PORT_CONNECTION = 9999;

    public static void init() throws IOException, LifecycleException {
        final var tomcat = new Tomcat();
        final var baseDir = Files.createTempDirectory(BASE_DIRECTORY);
        baseDir.toFile().deleteOnExit();
        tomcat.setBaseDir(baseDir.toAbsolutePath().toString());

        final var connector = new Connector();
        connector.setPort(PORT_CONNECTION);
        tomcat.setConnector(connector);

        tomcat.getHost().setAppBase(".");
        tomcat.addWebapp("", ".");

        tomcat.start();
        tomcat.getServer().await();
    }
}
