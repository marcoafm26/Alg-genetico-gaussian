package domain.impl;

import domain.Individuo;
import domain.IndividuoRepresentacaoReal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IndividuoSchwefelFunction extends IndividuoRepresentacaoReal {


    @Override
    public Individuo getNewInstance(List<Double> genes) {
        return new IndividuoSchwefelFunction(nDimension, domain_x, domain_y, chanceMutacao,genes);
    }

    public IndividuoSchwefelFunction(int nRainhas, double domain_x, double domain_y, double chanceMutacao) {
        super(nRainhas, domain_x, domain_y, chanceMutacao);
    }

    public IndividuoSchwefelFunction(int nRainhas, double domain_x, double domain_y, double chanceMutacao, List<Double> genes) {
        super(nRainhas, domain_x, domain_y, chanceMutacao,genes);
    }

    //TODO implementar os métodos da classe
    // numero de colisoes: diagonal
    @Override
    public Double avaliar() {
        double sum = 0;
        double value = 0;

            for (int j = 0; j < this.nDimension; j++) {
                value += this.genes.get(j) * Math.sin(Math.sqrt(Math.abs(this.genes.get(j))));
            }
            sum = 418.9829 * this.nDimension - value;
            return sum;
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
        List<Double> genes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            genes.add(i,420.9687);
        }

        Individuo individuo = new IndividuoPermFunction(5,-500,500,0.1,genes);
        System.out.println(individuo.getAvaliacao());
    }
}
