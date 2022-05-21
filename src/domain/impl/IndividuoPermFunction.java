package domain.impl;

import domain.IndividuoRepresentacaoReal;
import domain.Individuo;
import java.util.*;

public class IndividuoPermFunction extends IndividuoRepresentacaoReal {


    @Override
    public Individuo getNewInstance(List<Double> genes) {
        return new IndividuoPermFunction(nDimension, domain_x, domain_y, chanceMutacao,genes);
    }

    public IndividuoPermFunction(int nRainhas, double domain_x, double domain_y, double chanceMutacao) {
        super(nRainhas, domain_x, domain_y, chanceMutacao);
    }

    public IndividuoPermFunction(int nRainhas, double domain_x, double domain_y, double chanceMutacao,List<Double> genes) {
        super(nRainhas, domain_x, domain_y, chanceMutacao,genes);
    }

    //TODO implementar os métodos da classe
    // numero de colisoes: diagonal
    @Override
    public Double avaliar() {
        double sum =0;

        for (int i = 1; i <= nDimension; i++) {
            double value = 0;
            for (int j = 1; j <= this.nDimension; j++) {
                double inner1 = Math.pow(j, i)+ 0.5;
                double inner2 = Math.pow(this.genes.get(j-1)/j,i) - 1;
                value = inner1 * inner2 + value;
            }
            sum += Math.pow(value,2);
        }
        this.avaliacao = sum;
        return this.avaliacao;
    }


    @Override
    public List<Individuo> getFilhos(Individuo ind) {
        return super.getFilhos(ind);
    }

       @Override
    public Individuo getMutante() {
        return super.getMutante();
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void main(String[] args) {
        //List<Integer> genes = Arrays.asList(0, 1, 2, 3, 4, 5, 6 ,7);
        List<Double> genes = Arrays.asList(1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0);
        Individuo individuo = new IndividuoPermFunction(8,-8,8,0.1,genes);
        System.out.println(individuo.getAvaliacao());
    }
}
