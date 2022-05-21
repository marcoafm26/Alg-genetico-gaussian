package domain.impl;

import domain.Individuo;
import domain.IndividuoRepresentacaoReal;

import java.util.Arrays;
import java.util.List;

public class IndividuoCrossInTray extends IndividuoRepresentacaoReal {


    @Override
    public Individuo getNewInstance(List<Double> genes) {
        return new IndividuoCrossInTray(nDimension, domain_x, domain_y, chanceMutacao,genes);
    }

    public IndividuoCrossInTray(int nRainhas, double domain_x, double domain_y, double chanceMutacao) {
        super(nRainhas, domain_x, domain_y, chanceMutacao);
    }

    public IndividuoCrossInTray(int nRainhas, double domain_x, double domain_y, double chanceMutacao, List<Double> genes) {
        super(nRainhas, domain_x, domain_y, chanceMutacao,genes);
    }

    //TODO implementar os métodos da classe
    // numero de colisoes: diagonal
    @Override
    public Double avaliar() {
        double sum;
        double value = 0;
            for (int j = 1; j <= this.nDimension; j++) {
                value += this.genes.get(j-1) * Math.sin(Math.sqrt(Math.abs(this.genes.get(j-1))));
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


    public static void main(String[] args) {
        //List<Integer> genes = Arrays.asList(0, 1, 2, 3, 4, 5, 6 ,7);
        List<Double> genes = Arrays.asList(420.9687,420.9687,420.9687,420.9687,420.9687,420.9687,420.9687,420.9687);
        Individuo individuo = new IndividuoCrossInTray(8,-500,500,0.1,genes);
        System.out.println(individuo.getAvaliacao());
    }
}
