public class GeneticAlgorithm {
    public static final int[] TARGET_CHROMOSOME = {1, 1, 1, 1, 1, 1, 1};
    public static final int POPULATION_SIZE = 8;

    public Population evolve(Population population){
        return mutatePopulation(crossoverPopulation(population));
    }

    private Population crossoverPopulation(Population population){
        return population;
    }

    private Population mutatePopulation(Population population){
        return population;
    }
}
