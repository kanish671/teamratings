package anishk.developer.teamratings.constants;

public enum Result {
    W("Win"),
    D("Draw"),
    L("Loss");

    private String value;

    Result(String value) {
        this.value = value;
    }

    public String getResultValue() {
        return value;
    }
}
