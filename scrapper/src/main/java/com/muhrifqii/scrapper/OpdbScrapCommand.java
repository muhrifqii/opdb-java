package com.muhrifqii.scrapper;

import java.util.concurrent.Callable;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import com.muhrifqii.scrapper.serde.OutputWriter;
import com.muhrifqii.scrapper.serde.OutputWriterImpl;

import lombok.RequiredArgsConstructor;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.HelpCommand;
import picocli.CommandLine.Option;

@Component
@RequiredArgsConstructor
@Command(name = "opdb-scrapper", mixinStandardHelpOptions = true, description = "OPDB command that is used to scrap the One Piece Wikia page", subcommands = {
        HelpCommand.class })
public class OpdbScrapCommand implements Callable<Integer> {

    private final ScrapperProvider scrapperProvider;
    private final OutputWriter outputWriter;

    @Option(names = { "-o", "--output" }, defaultValue = "./output/", description = "Output directory")
    private String outputDir;

    @Override
    public Integer call() throws Exception {
        final var totalDf = scrapperProvider.dfScrapper()
                .getDevilFruitTypeInfo();
        outputWriter.dumpToJson(totalDf, outputDir, "totalDf.json");
        return 0;
    }

    public static void main(String[] args) {
        final var conn = Jsoup.newSession()
                .timeout(20 * 1000)
                .header("Accept-Language", "*")
                .userAgent(
                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36");
        final var url = "https://onepiece.fandom.com/wiki";
        final var dependencyProvider = new ScrapperProviderImpl(url, conn);
        final var inst = new OpdbScrapCommand(dependencyProvider, new OutputWriterImpl());

        final var exitCode = new CommandLine(inst)
                .execute(args);
        System.exit(exitCode);
    }
}
