package com.muhrifqii.scrapper.df;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.jsoup.Connection;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.muhrifqii.scrapper.errors.PageSourceInvalidException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DfScrapper {
    private final Connection jsoupConn;
    private final String baseUrl;

    private static final String DF_URL = "/Devil_Fruit";

    public List<DevilFruitTypeInfo> getDevilFruitTypeInfo() throws IOException {
        final var page = jsoupConn.newRequest(baseUrl + DF_URL).get();

        final var tableRows = Optional.ofNullable(page
                .select("span#Devil_Fruit_Count")
                .first())
                .map(Element::parent)
                .map(Element::nextElementSiblings)
                .map(els -> els.select("table.wikitable").first())
                .map(el -> el.select("tr"))
                .orElseThrow(() -> new PageSourceInvalidException());

        return tableRows.stream()
                .filter(el -> Objects.equals(
                        "td",
                        el.firstElementChild().tagName()))
                .limit(4)
                .map(el -> el.select("td"))
                .sorted((el1, el2) -> el1.get(0).text()
                        .compareTo(el2.get(0).text()))
                .map(this::mapElementToDevilFruitTypeInfo)
                .peek(System.out::println)
                .toList();
    }

    private DfType mapElementToDfType(Elements els) {
        final var typeStr = els.get(0)
                .text()
                .toUpperCase();
        return Enum.valueOf(DfType.class, typeStr);
    }

    private DevilFruitTypeInfo mapElementToDevilFruitTypeInfo(Elements els) {
        final var type = mapElementToDfType(els);
        final var cannonCount = Integer.parseInt(
                els.get(1).ownText());
        final var nonCannonCount = Integer.parseInt(
                els.get(2).ownText());
        return new DevilFruitTypeInfo(type, cannonCount, nonCannonCount);
    }

    void getDevilFruits(DfType type) {
    }

}
