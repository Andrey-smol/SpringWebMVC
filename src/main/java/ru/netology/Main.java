package ru.netology;

import org.apache.catalina.LifecycleException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws LifecycleException, IOException {
        ServerTomcatInit.init();
    }
}