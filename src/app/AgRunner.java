package app;

import domain.Ag;
import domain.Individuo;
import domain.factory.IndividuoCrossInTrayFactory;
import domain.factory.IndividuoFactory;
import domain.factory.IndividuoPermFunctionFactory;
import domain.factory.IndividuoSchwefelFunctionFactory;
import domain.impl.IndividuoPermFunction;

public class AgRunner {

    public static void main(String[] args) {
        final int N_POP = 20;
        final int N_DIMENSIONS = 5;
        final int N_ELITE = 4;
        final int N_GER = 1000;
        final boolean IS_MAX = false;

        final double DOMAIN_X = -500;
        final double DOMAIN_Y = 500;
        final double CHANCE_MUTACAO = 0.1;


        IndividuoFactory indFactory = new IndividuoSchwefelFunctionFactory(N_DIMENSIONS,DOMAIN_X,DOMAIN_Y,CHANCE_MUTACAO);
        Ag ag = new Ag();
        Individuo ind =  ag.executar(N_POP, indFactory, N_ELITE, IS_MAX, N_GER);


    }
}
