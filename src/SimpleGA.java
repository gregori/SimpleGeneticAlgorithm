import java.util.Random;

public class SimpleGA {
    Population population = new Population();
    Individual fittest;
    Individual secondFittest;
    int generationCount = 0;

    public static void main(String[] args) {

        Random rn = new Random();

        SimpleGA demo = new SimpleGA();

        //Inicializar população
        demo.population.initializePopulation(10);

        //Calcule a aptidão de cada indivíduo
        demo.population.calculateFitness();

        System.out.println("Generation: " + demo.generationCount + " Fittest: " + demo.population.fittest );

        //Enquanto a população obtém um indivíduo com aptidão máxima
        while (demo.population.fittest < 90) {
            ++demo.generationCount;

            //Fazer seleção
            demo.selection();

            //Fazer cruzamento
            demo.crossover();

            //Do mutation under a random probability
            if (rn.nextInt()%7 < 5) {
                demo.mutation();
            }

            //Adicionar descendentes mais aptos à população
            demo.addFittestOffspring();

            //Calcular novo valor de fitness
            demo.population.calculateFitness();

            System.out.println("Generation: " + demo.generationCount + " Fittest: " + demo.population.fittest);
        }

        System.out.println("\nSolution found in generation " + demo.generationCount);
        System.out.println("Fitness: " + demo.population.getFittest().fitness);
        System.out.print("Genes: ");

        for (int i = 0; i < 5; i++) {
            System.out.println(demo.population.getFittest().genes[i]);
        }
        
        System.out.println(demo.fittest);
        System.out.println("");

    }

    //Seleção
    void selection() {

        //Selecione o indivíduo mais apto
        fittest = population.getFittest();

        //Selecione o segundo indivíduo mais apto
        secondFittest = population.getSecondFittest();
    }

    //Cruzamento
    void crossover() {
        Random rn = new Random();

        //Selecione um ponto de cruzamento aleatório
        int crossOverPoint = rn.nextInt(population.individuals[0].geneLength);

        //Trocar valores entre os pais
        for (int i = 0; i < crossOverPoint; i++) {
            int temp = fittest.genes[i];
            fittest.genes[i] = secondFittest.genes[i];
            secondFittest.genes[i] = temp;

        }

    }

    //Mutação
    void mutation() {
        Random rn = new Random();

        //Selecione um ponto de mutação aleatório
        int mutationPoint = rn.nextInt(population.individuals[0].geneLength);

        //Inverter valores no ponto de mutação
        if (fittest.genes[mutationPoint] == 0) {
            fittest.genes[mutationPoint] = 1;
        } else {
            fittest.genes[mutationPoint] = 0;
        }

        mutationPoint = rn.nextInt(population.individuals[0].geneLength);

        if (secondFittest.genes[mutationPoint] == 0) {
            secondFittest.genes[mutationPoint] = 1;
        } else {
            secondFittest.genes[mutationPoint] = 0;
        }
    }

    //Obter descendentes mais aptos
    Individual getFittestOffspring() {
        if (fittest.fitness > secondFittest.fitness) {
            return fittest;
        }
        return secondFittest;
    }


    //Substitua o indivíduo menos apto da prole mais apto
    void addFittestOffspring() {

        //Atualizar os valores de aptidão dos descendentes
        fittest.calcFitness();
        secondFittest.calcFitness();

        //Obter índice do indivíduo menos apto
        int leastFittestIndex = population.getLeastFittestIndex();

        //Substitua o indivíduo menos apto da prole mais apto
        population.individuals[leastFittestIndex] = getFittestOffspring();
    }
}
