public class GeneticAlgorithm {
    public static final int[] TARGET_CHROMOSOME = {1, 1, 1, 1, 1, 1, 1};
    public static final int NUMB_OF_ELITE_CHROMOSOMES = 2;
    public static final double MUTATION_RATE = 0.02;

    public static int POPULATION_SIZE;

    public GeneticAlgorithm(int size){
        POPULATION_SIZE = size;
    }

    public Population evolve(Population population){
        return mutatePopulation(crossoverPopulation(population));
    }

    private Population crossoverPopulation(Population population){
        Population crossoverPopulation = new Population(population.getChromosomes().length);
        for(int i = 0; i < NUMB_OF_ELITE_CHROMOSOMES; i++){
            crossoverPopulation.getChromosomes()[i] = population.getChromosomes()[i];
        }

        for(int i = NUMB_OF_ELITE_CHROMOSOMES; i < population.getChromosomes().length; i++){
            Chromosome chromosome1 = selectChromosomeByRoulette(population);
            Chromosome chromosome2 = selectChromosomeByRoulette(population);
            crossoverPopulation.getChromosomes()[i] = crossoverChromosome(chromosome1, chromosome2);
        }

        return crossoverPopulation;
    }

    private Population mutatePopulation(Population population){
        Population mutatePopulation = new Population(population.getChromosomes().length);
        for(int i = 0; i < NUMB_OF_ELITE_CHROMOSOMES; i++){
            mutatePopulation.getChromosomes()[i] = population.getChromosomes()[i];
        }

        for(int i = NUMB_OF_ELITE_CHROMOSOMES; i < population.getChromosomes().length; i++){
            mutatePopulation.getChromosomes()[i] = mutateChromosome(population.getChromosomes()[i]);
        }

        return mutatePopulation;
    }

    private Chromosome crossoverChromosome(Chromosome chromosome1, Chromosome chromosome2){
        Chromosome crossoverChromosome = new Chromosome(TARGET_CHROMOSOME.length);
        for(int i = 0; i < chromosome1.getGenes().length; i++){
            if(Math.random() < 0.5){
                crossoverChromosome.getGenes()[i] = chromosome1.getGenes()[i];
            }else{
                crossoverChromosome.getGenes()[i] = chromosome2.getGenes()[i];
            }
        }
        return crossoverChromosome;
    }

    private Chromosome mutateChromosome(Chromosome chromosome){
        Chromosome mutateChromosome = new Chromosome(TARGET_CHROMOSOME.length);

        for(int i = 0; i < chromosome.getGenes().length; i++){
            if(Math.random() < MUTATION_RATE){
                if(Math.random() < 0.5){
                    mutateChromosome.getGenes()[i] = 1;
                }else{
                    mutateChromosome.getGenes()[i] = 0;
                }
            }else{
                mutateChromosome.getGenes()[i] = chromosome.getGenes()[i];
            }
        }

        return mutateChromosome;
    }

    private Chromosome selectChromosomeByRoulette(Population population){
        Chromosome rouletteChromosome = new Chromosome(TARGET_CHROMOSOME.length);
        boolean solutionFound = false;
        int rouletteValue = calculateRouletteValue(population);
        double result = 0;
        int i = 0;
        double rand = Math.random();

        while(!solutionFound){
            result += (double)convertChromosomeToNumber(population.getChromosomes()[i]) / rouletteValue;
            if(rand < result){
                rouletteChromosome = population.getChromosomes()[i];
                solutionFound = true;
            }
            i++;
        }

        return rouletteChromosome;
    }

    private int calculateRouletteValue(Population population){
        int result = 0;

        for(int i = 0; i < population.getChromosomes().length; i++){
            result += convertChromosomeToNumber(population.getChromosomes()[i]);
        }

        return result;
    }

    private int convertChromosomeToNumber(Chromosome chromosome){
        String temp = "";
        for(int i = 0; i < chromosome.getGenes().length; i++){
            temp += chromosome.getGenes()[i];
        }

        int number = Integer.parseInt(temp, 2);

        return 2 * number * number + 2;
    }
}
