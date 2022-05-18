package domain.impl;

import domain.IndividuoRepresentacaoReal;
import domain.Individuo;
import java.util.*;

public class IndividuoPermFunction extends IndividuoRepresentacaoReal {



    public IndividuoPermFunction(int nRainhas) {
        nDimension = nRainhas;
        random = new Random();
        genes = super.geraGenes(this.nDimension);
    }

    public IndividuoPermFunction(int nRainhas, List<Double> genes) {
        this.nDimension = nRainhas;
        random = new Random();
        this.genes = genes;
    }

    //TODO implementar os métodos da classe
    // numero de colisoes: diagonal
    @Override
    public Double avaliar() {
        double sum =0;
        double value = 0;
        for (int i = 1; i < nDimension; i++) {
            for (int j = 1; j <= this.nDimension; j++) {
            value += Math.pow((Math.pow(j, i)+ 0.5)*(this.genes.get(j-1)/j-1), 2);
        }
            sum += value;
        }

        return avaliacao=sum;
    }

    

    public static void main(String[] args) {
        //List<Integer> genes = Arrays.asList(0, 1, 2, 3, 4, 5, 6 ,7);
        List<Integer> genes = Arrays.asList(5, 2, 7, 0, 3, 6, 4 ,1);
        //Individuo individuo = new IndividuoPermFunction(8,genes);
       // System.out.println(individuo.getAvaliacao());
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

    @Override
    public int compareTo(Individuo o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
