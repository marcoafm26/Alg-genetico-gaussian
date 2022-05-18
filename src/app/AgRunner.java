package app;

import domain.Ag;
import domain.Individuo;
import domain.factory.IndividuoFactory;
import domain.factory.IndividuoPermFunctionFactory;

public class AgRunner {
    public static String imprimir(Individuo ind, int nGer){
        String aux = "";
        aux +="Colisoes: "+ ind.getAvaliacao()+"\n";
        aux +="Genes: " + ind.getGenes().toString();
        aux +="\nGeracao:  " + nGer;
        return aux;
    }
    public static void main(String[] args) {
        final int N_POP = 20;
        final int N_DIMENSIONS = 8;
        final int N_ELITE = 4;
        final int N_GER = 1000;
        final boolean IS_MAX = false;

        final int N_FUNCTION = 44;
        final double DOMAIN_X = 5;
        final double DOMAIN_Y = 5;



        IndividuoFactory indFactory = new IndividuoPermFunctionFactory(N_DIMENSIONS);
        Ag ag = new Ag();
        Individuo ind =  ag.executar(N_POP, indFactory, N_ELITE, IS_MAX, N_GER,N_FUNCTION,DOMAIN_X,DOMAIN_Y);



        System.out.println(imprimir(ind,N_GER));

    }
}
