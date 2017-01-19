/**
 * Created by Edvard Piri on 15.01.2017.
 */
public class Country {
    private String name;
    private Integer population;

    public Country(String name, int population) {
        this.name = name;
        this.population = population;
    }

    public void changePopulation(int delta) throws Exception{
        if (delta > population)
            throw new Exception("error");
        population -= delta;
    }

    public String getName() {
        return name;
    }

    public Integer getPopulation() {
        return population;
    }
}
