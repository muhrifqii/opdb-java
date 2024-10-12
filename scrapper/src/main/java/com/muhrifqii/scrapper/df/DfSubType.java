package com.muhrifqii.scrapper.df;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DfSubType {
    ANCIENT_ZOAN("Ancient_Zoan"),
    MYTHICAL_ZOAN("Mythical_Zoan"),
    ARTIFICIAL("Artificial_Devil_Fruit");

    private final String value;

    public String getPath() {
        return switch (this) {
            case ANCIENT_ZOAN, MYTHICAL_ZOAN -> "/Zoan#" + value;
            default -> "/" + value;
        };
    }
}
