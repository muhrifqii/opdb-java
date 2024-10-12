package com.muhrifqii.scrapper.df;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DfType {
    LOGIA("Logia"),
    ZOAN("Zoan"),
    PARAMECIA("Paramecia"),
    UNDETERMINED("");

    private final String value;

    public String getPath() {
        return "/" + value;
    }
}
