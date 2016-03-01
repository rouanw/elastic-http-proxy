package example;

public class Animal {
    private String species;
    private String noise;

    public Animal() {

    }

    public Animal(String species, String noise) {

        this.species = species;
        this.noise = noise;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getNoise() {
        return noise;
    }

    public void setNoise(String noise) {
        this.noise = noise;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "species='" + species + '\'' +
                ", noise='" + noise + '\'' +
                '}';
    }
}
