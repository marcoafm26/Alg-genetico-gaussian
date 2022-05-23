package domain.impl;

import domain.Individuo;
import domain.IndividuoRepresentacaoReal;

import java.util.Arrays;
import java.util.List;

public class IndividuoCrossInTrayFunction extends IndividuoRepresentacaoReal {


    @Override
    public Individuo getNewInstance(List<Double> genes) {
        return new IndividuoCrossInTrayFunction(nDimension, domain_x, domain_y, chanceMutacao,genes);
    }

    public IndividuoCrossInTrayFunction(int nRainhas, double domain_x, double domain_y, double chanceMutacao) {
        super(nRainhas, domain_x, domain_y, chanceMutacao);
    }

    public IndividuoCrossInTrayFunction(int nRainhas, double domain_x, double domain_y, double chanceMutacao, List<Double> genes) {
        super(nRainhas, domain_x, domain_y, chanceMutacao,genes);
    }

    //TODO implementar os métodos da classe
    // numero de colisoes: diagonal
    @Override
    public Double avaliar() {
        double functionValue =0;
        double x1= this.genes.get(0);
        double x2 = this.genes.get(1);


                double inner1 = (Math.sqrt(Math.pow(x1,2)+Math.pow(x2,2))/Math.PI);
                double inner2 = Math.exp(Math.abs(100 - inner1));Math.abs(100 - inner1);
                double inner3 = Math.abs((Math.sin(x1)*Math.sin(x2)*inner2))+1;
                functionValue = -0.0001 * Math.pow(inner3,0.1);
        this.avaliacao = functionValue;
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
        Individuo individuo = new IndividuoCrossInTrayFunction(8,-8,8,0.1,genes);
        System.out.println(individuo.getAvaliacao());
    }
}
