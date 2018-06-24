import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public class Population {
    private Chromosome[] chromosomes;

    public Population(int length){
        chromosomes = new Chromosome[length];
    }

    public Population initializePopulation(){
        for(int i = 0; i < chromosomes.length; i++){
            chromosomes[i] = new Chromosome(GeneticAlgorithm.TARGET_CHROMOSOME.length).initializeChromosome();
        }
        sortChromosomesByFitness();
        return this;
    }

    public void sortChromosomesByFitness(){
        Arrays.sort(chromosomes, (chromosomes1, chromosome2) -> {
            int flag = 0;
            if(chromosomes1.getFitness() > chromosome2.getFitness()){
                flag = -1;
            }else if(chromosomes1.getFitness() < chromosome2.getFitness()){
                flag = 1;
            }
            return flag;
        });
    }
}
