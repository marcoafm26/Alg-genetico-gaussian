package domain;

import java.util.List;

public abstract class Individuo implements Comparable<Individuo>{

    public Double avaliacao;



    public List<Double> genes;

    public abstract Double avaliar();

    public abstract List<Individuo> getFilhos(Individuo ind);

    public abstract Individuo getMutante();

    public Double getAvaliacao() {
        if (this.avaliacao == null) {
            this.avaliacao = this.avaliar();
        }
        return this.avaliacao;
    }

    public void setAvaliacao(Double avaliacao){
        this.avaliacao = avaliacao;
    }

    public List<Double> getGenes() {
        return genes;
    }


    public abstract String toString();

    @Override
    public int compareTo(Individuo o) {
        if (this.getAvaliacao() >o.getAvaliacao())
            return 1;
        else if (this.getAvaliacao() < o.getAvaliacao()) {
            return -1;
        }
        return 0;
    }

}
