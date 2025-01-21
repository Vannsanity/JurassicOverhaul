package net.vannsanity.jurassicoverhaul.utils;

import java.util.Map;

public class MobDNA {
    private final String mobName;
    private final Map<String, Object> properties;

    public MobDNA(String mobName, Map<String, Object> properties) {
        this.mobName = mobName;
        this.properties = properties;
    }

    public String getMobName() {
        return mobName;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }
}
