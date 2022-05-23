/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import static java.util.Collections.shuffle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Marco Aurélio
 */
public abstract class IndividuoRepresentacaoReal extends Individuo{
    
    public Random random;
    public int nDimension;
    public double chanceMutacao;

    public double domain_x;

    public double domain_y;

    public abstract Individuo getNewInstance(List<Double> genes);

    public IndividuoRepresentacaoReal(int nRainhas,double domain_x, double domain_y, double chanceMutacao) {
        this.nDimension = nRainhas;
        this.domain_x = domain_x;
        this.domain_y = domain_y;
        this.chanceMutacao = chanceMutacao;
        this.random = new Random();
        genes = geraGenes(this.nDimension);

    }

    public IndividuoRepresentacaoReal(int nRainhas,double domain_x, double domain_y, double chanceMutacao, List<Double> genes) {
        this.nDimension = nRainhas;
        this.domain_x = domain_x;
        this.domain_y = domain_y;
        this.chanceMutacao = chanceMutacao;
        this.genes = genes;
        this.random = new Random();
    }


    protected List<Double> geraGenes(int nDimension) {
        Map<Double,Double> hash = new HashMap<>();
        double rand;
        List<Double> genes = new ArrayList<>(nDimension);
        for (int i = 0; i < nDimension; i++) {
            rand = this.domain_x + (this.domain_y-this.domain_x)*random.nextDouble();
            while (hash.get(rand) != null)
                rand = this.domain_x + (this.domain_y-this.domain_x)*random.nextDouble();
            genes.add(rand);
            hash.put(rand, rand);
        shuffle(genes);
        }
        return genes;
    }
    @Override
    public List<Individuo> getFilhos(Individuo ind) {
        double alpha;
        List<Double> genesFilho_1 = new ArrayList<>(nDimension);
        List<Double> genesFilho_2 = new ArrayList<>(nDimension);
        List<Individuo> filhos = new LinkedList<>();
        double ruido;
        for (int i = 0; i < this.nDimension; i++) {
            alpha = this.random.nextGaussian();
            ruido = alpha * Math.abs(this.genes.get(i) - ind.genes.get(i));
            double valueFilho_1 = boundDomain(this.genes.get(i) + ruido);

            alpha = this.random.nextGaussian();
            ruido = alpha * Math.abs(this.genes.get(i) - ind.genes.get(i));
            double valueFilho_2 = boundDomain(ind.genes.get(i) + ruido);

            genesFilho_1.add(i,valueFilho_1);
            genesFilho_2.add(i,valueFilho_2);

        }

        filhos.add(this.getNewInstance(genesFilho_1));
        filhos.add(this.getNewInstance(genesFilho_2));
        return filhos;
    }
    @Override
    public Individuo getMutante() {
        List<Double> genesFilho = new ArrayList<>(genes);
        int cont= 0;
        for (int i=0; i < this.genes.size();i++) {
            double chance = random.nextDouble();
            if(chance <= chanceMutacao){
               double ruido = random.nextGaussian();
               genesFilho.set(i, boundDomain(genesFilho.get(i) + ruido));
               cont++;
            }
        }
        if (cont == 0){
            int pos = random.nextInt(nDimension);
            double point = genesFilho.get(pos) +  random.nextGaussian();
            genesFilho.set(pos,boundDomain(point));
        }

        return this.getNewInstance(genesFilho);
    }

    public double boundDomain(double point){

        if (point < this.domain_x)
            point = this.domain_x-(this.domain_x * 0.01);
        else if (point > this.domain_y )
            point = this.domain_y-(this.domain_y * 0.01);
        return point;
    }
    @Override
    public String toString() {
        return String.format("nRainhas: %s \nAvaliação: %s", nDimension, avaliacao);
    }

}
