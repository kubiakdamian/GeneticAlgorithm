import java.util.Arrays;

public class Driver {

    public static void main(String[] args){
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm((int) Math.ceil(Math.random() * 15 + 5));
        Population population = new Population(GeneticAlgorithm.POPULATION_SIZE).initializePopulation();
        System.out.println("----------------------------------------------");
        System.out.println("Generation # 0 " + " | Fittest chromosome fitness: " + population.getChromosomes()[0].getFitness());
        printPopulation(population, "Target chromosome: " + Arrays.toString(GeneticAlgorithm.TARGET_CHROMOSOME));

        int generationNumber = 0;
        while(population.getChromosomes()[0].getFitness() < 2 * 127 * 127 + 2){
            generationNumber++;
            System.out.println("\n----------------------------------------------");
            population = geneticAlgorithm.evolve(population);
            population.sortChromosomesByFitness();

            System.out.println("Generation # " + generationNumber + " | Fittest chromosome fitness: " + population.getChromosomes()[0].getFitness());
            printPopulation(population, "Target chromosome: " + Arrays.toString(GeneticAlgorithm.TARGET_CHROMOSOME));
        }
    }

    public static void printPopulation(Population population, String heading){
        System.out.println(heading);
        System.out.println("---------------------------------------------");
        for(int i = 0; i < population.getChromosomes().length; i++){
            System.out.println("Chromosome #" + i + "   : " + Arrays.toString(population.getChromosomes()[i].getGenes()) + " | Fitness: " + population.getChromosomes()[i].getFitness());
        }
    }
}
