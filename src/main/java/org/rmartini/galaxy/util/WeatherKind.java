package org.rmartini.galaxy.util;

public enum WeatherKind {

    DROUGHT ("DROUGHT"),
    RAIN    ("RAIN"),
    SUNNY   ("SUNNY"),
    OPTIMUM ("OPTIMUM");

    private final String kind;

    WeatherKind(String kind) {
        this.kind = kind;
    }

    public String getKind() {
        return this.kind;
    }

    public static boolean contains(String kind){
        if(kind.equalsIgnoreCase(DROUGHT.kind) ||
                kind.equalsIgnoreCase(RAIN.kind) ||
                kind.equalsIgnoreCase(SUNNY.kind) ||
                kind.equalsIgnoreCase(OPTIMUM.kind)) {
            return true;
        }
        return false;
    }

}
