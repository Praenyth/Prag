package nyth.prae.awesome.plugins.prag.enums;

public enum Role {
    TAGGER("&cT"),
    RUNNER("&aR"),
    SPECTATOR("&7S"),
    FROZEN("&bF");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
