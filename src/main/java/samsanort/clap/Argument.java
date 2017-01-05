package samsanort.clap;

/**
 * Representation of a command line argument (flag-like).
 * f.i.: "-verbose"
 */
public class Argument {

    private String name;
    private String description;

    /**
     * C'tor.
     *
     * @param name        Name of the argument - as it will be provided in the command line.
     * @param description Description of the argument - what is this argument used for.
     */
    public Argument(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }
}
