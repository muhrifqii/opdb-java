package com.muhrifqii.scrapper;

import org.jsoup.Connection;

import com.muhrifqii.scrapper.df.DfScrapper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Accessors(fluent = true)
public final class ScrapperProviderImpl implements ScrapperProvider {

    private final String baseUrl;
    private final Connection jsoupConnection;

    @Getter(lazy = true)
    private final DfScrapper dfScrapper = initDfScrapper();

    private DfScrapper initDfScrapper() {
        return new DfScrapper(jsoupConnection, baseUrl);
    }
}
