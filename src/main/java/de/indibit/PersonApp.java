package de.indibit;


import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class PersonApp {
    public static void main(String... args) {
        Quarkus.run(args);
    }
}
