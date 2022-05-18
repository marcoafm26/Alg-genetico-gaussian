/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.Individuo;
import domain.impl.IndividuoPermFunction;
import domain.impl.IndividuoPermFunction;
import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author aluno
 */
public class IndividuoRepresentacaoReal extends Individuo{
    
    public Random random;
    public int nDimension;
    public double chanceMutacao;
    
    @Override
    public Double avaliar() {return null;}
    
    protected List<Double> geraGenes(int nGenes) {
        Map<Integer,Integer> hash = new HashMap<>();
        int rand;
        List<Double> genes = new ArrayList<>(nGenes);
        for (int i = 0; i < nGenes; i++) {
            rand = random.nextInt(nGenes);
            while (hash.get(rand) != null)
                rand = random.nextInt(nGenes);
            genes.add((double) rand);
            hash.put(rand, rand);

        }
        return genes;
    }
    @Override
    public List<Individuo> getFilhos(Individuo ind) {
        int rand = random.nextInt(nDimension);
        double alpha;
        List<Double> genesFilho_1 = new ArrayList<>(this.nDimension);
        List<Double> genesFilho_2 = new ArrayList<>(this.nDimension);
        List<Individuo> filhos = new LinkedList<>();

        for (int i = 0; i < this.nDimension; i++) {
            alpha = random.nextGaussian();
            double valueFilho_1 = this.genes.get(i) + alpha * Math.abs(this.genes.get(i) - ind.genes.get(i));

            alpha = random.nextGaussian();
            double valueFilho_2 = ind.genes.get(i) + alpha * Math.abs(this.genes.get(i) - ind.genes.get(i));

            genesFilho_1.set(i,valueFilho_1);
            genesFilho_2.set(i,valueFilho_2);

        }

        filhos.add(new IndividuoPermFunction(this.nDimension, genesFilho_1));
        filhos.add(new IndividuoPermFunction(this.nDimension, genesFilho_2));
        return filhos;
    }
    @Override
    public Individuo getMutante() {
        List<Double> genesFilho = new ArrayList<>(this.nDimension);
        int cont= 0;
        for (int i=0; i < this.genes.size();i++) {
            double chance = random.nextDouble();
            if(chance <= chanceMutacao){
               double ruido = random.nextGaussian();
               genesFilho.set(i, this.genes.get(i) + ruido);
               cont++;
            }
        }
        if (cont == 0){
            int pos = random.nextInt();
            genes.set(pos,this.genes.get(pos)+random.nextGaussian());
        }

        return new IndividuoPermFunction(this.nDimension, genes);
    }

    public double somatorio(List<Double> list){
        double soma=0;
        for(Double value: list){
            soma+=value;
        }
        
        return soma;
    }
            
    @Override
    public String toString() {
        return String.format("nRainhas: %s \nAvaliação: %s", nDimension, avaliacao);
    }

    @Override
    public int compareTo(Individuo o) {
        return this.avaliacao.compareTo(o.avaliacao);
    }

}
