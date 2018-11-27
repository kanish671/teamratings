package anishk.developer.teamratings.constants;

public enum Venue {
    HOME("Home"),
    AWAY("Away"),
    NEUTRAL("Neutral");

    private String value;

    Venue(String value) {
        this.value = value;
    }

    public String getVenueValue() {
        return value;
    }
}
