package nyth.prae.awesome.plugins.prag.enums;

public enum Roles {
    TAGGER("&cT"),
    RUNNER("&aR"),
    SPECTATOR("&7S"),
    FROZEN("&bF");

    private String name;

    Roles(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
