package com.muhrifqii.scrapper;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import picocli.CommandLine;
import picocli.CommandLine.IFactory;

@RequiredArgsConstructor
@SpringBootApplication
public class OpdbScrapApp implements CommandLineRunner, ExitCodeGenerator {

    private final OpdbScrapCommand cli;
    private IFactory factory;

    @Getter
    private int exitCode;

    @Override
    public void run(String... args) throws Exception {
        exitCode = new CommandLine(cli).execute(args);
    }

    public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(OpdbScrapApp.class, args)));
    }

}
