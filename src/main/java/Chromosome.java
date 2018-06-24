import java.util.Arrays;

public class Chromosome {
    private boolean isFitnessChanged = true;
    private int[] genes;
    private int fitness = 0;

    public Chromosome(int length){
        genes = new int[length];
    }

    public Chromosome initializeChromosome(){
        for(int i = 0; i < genes.length; i++){
            if(Math.random() >= 0.5){
                genes[i] = 1;
            }else{
                genes[i] = 0;
            }
        }
        return this;
    }

    public int recalculateFitness(){
        int chromosomeFitness = 0;
        for(int i = 0; i < genes.length; i++){
            if(genes[i] == GeneticAlgorithm.TARGET_CHROMOSOME[i]){
                chromosomeFitness++;
            }
        }
        return chromosomeFitness;
    }

    public int[] getGenes(){
        isFitnessChanged = true;
        return genes;
    }

    public int getFitness(){
        if(isFitnessChanged){
            fitness = recalculateFitness();
            isFitnessChanged = false;
        }

        return fitness;
    }

    @Override
    public String toString(){
        return Arrays.toString(this.genes);
    }
}
