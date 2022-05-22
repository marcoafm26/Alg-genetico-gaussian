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
        nDimension = nRainhas;
        this.domain_x = domain_x;
        this.domain_y = domain_y;
        this.chanceMutacao = chanceMutacao;
        random = new Random();
        genes = geraGenes(this.nDimension,this.domain_x,this.domain_y);

    }

    public IndividuoRepresentacaoReal(int nRainhas,double domain_x, double domain_y, double chanceMutacao, List<Double> genes) {
        this.nDimension = nRainhas;
        this.domain_x = domain_x;
        this.domain_y = domain_y;
        this.chanceMutacao = chanceMutacao;
        this.genes = genes;
        random = new Random();
    }

    public IndividuoRepresentacaoReal() {
    }



    protected List<Double> geraGenes(int nDimension,double domain_x,double domain_y) {
        Map<Double,Double> hash = new HashMap<>();
        double rand;
        List<Double> genes = new ArrayList<>(nDimension);
        for (int i = 0; i < nDimension; i++) {
            rand = random.nextDouble(domain_x,domain_y);
            while (hash.get(rand) != null)
                rand = random.nextDouble(domain_x,domain_y);
            genes.add(rand);
            hash.put(rand, rand);

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
            alpha = random.nextGaussian();
            ruido = alpha * Math.abs(this.genes.get(i) - ind.genes.get(i));
            double valueFilho_1 = boundDomain(this.genes.get(i) + ruido);

            alpha = random.nextGaussian();
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
            genesFilho.set(pos,boundDomain(genesFilho.get(pos) +  random.nextGaussian()));
        }

        return this.getNewInstance(genesFilho);
    }

    public double boundDomain(double point){
        if (point < this.domain_x)
            point = this.domain_x;
        else if (point > this.domain_y )
            point = this.domain_y;
        return point;
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
