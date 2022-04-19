import java.util.Random;

public class Individual {
    int fitness     = 0;
    int[] genes     = new int[7];
    int geneLength  = 7;
    String[]names   = {"canivete", "feijao", "batatas", "lanterna", "saco de dormir", "corda", "bussola"};
    int[] points    = {10, 20, 15, 2, 30, 10, 30};
    int[] weight    = {1, 5, 10, 1, 7, 5, 1};
    int pesototal   = 0;

    public Individual() {
        Random rn = new Random();

        //Definir genes aleatoriamente para cada indivíduo
        for (int i = 0; i < genes.length; i++) {
            genes[i] = Math.abs(rn.nextInt() % 2);
        }

        fitness = 0;
    }

    //Calcular aptidão
    public void calcFitness(){
        fitness=0;
        int peso=0;

        for (int i = 0; i < names.length; i++) {
            if( genes[i] != 0){
                fitness += points[i];
                peso += weight[i];
            }
        }
        if (peso>15){
            fitness=0;
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < geneLength; i++) {
            str += names[i] + ": " + genes[i] + "\n";
            if(genes[i] ==1 ){
                pesototal += weight[i];
            }
        }
        str += "fitness :" + fitness + "\n" + "peso total: " + pesototal + "\n";
        return str;
    }
}
