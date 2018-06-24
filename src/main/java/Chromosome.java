public class Chromosome {
    private int[] genes;

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
}
