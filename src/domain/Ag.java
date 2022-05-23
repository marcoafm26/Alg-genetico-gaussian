package domain;

import domain.factory.IndividuoFactory;
import domain.impl.IndividuoPermFunction;
import domain.impl.IndividuoSchwefelFunction;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.shuffle;

public class Ag {

    public Individuo executar(int nPop, IndividuoFactory indFactory, int nElite, boolean isMax, int nGer) {
        // TODO for para criar nPop individuos em uma lista

        List<Individuo> indL = new LinkedList<>();
        List<Individuo> elite;
        List<Individuo> joinPop = new LinkedList<>();
        List<Individuo> newPop = new LinkedList<>();

        for (int i = 0; i < nPop; i++)
            indL.add(indFactory.getIndividuo());


        // TODO REPETICAO PARA QUANTIDADE DE GERACOES
        for (int rep= 0; rep < nGer; rep++) {

            // TODO aleatoriamente selecionar duplas de indivíduos para o crossover
            joinPop.addAll(indL);
            joinPop.addAll(geraDescendentes(indL)) ;
            indL.clear();

            // TODO AVALIACAO DE CADA INDIVIDUO
            for (Individuo ind : joinPop) {
                ind.getAvaliacao();
            }
            // TODO SELECAO DA ELITE
            elite = elite(joinPop,nElite,isMax);
            newPop.addAll(elite);
            joinPop.removeAll(elite);

            // TODO SELECAO DO RESTO DA POPULACAO
            List<Individuo> restanteList = rodaRoleta(joinPop, nPop - nElite, isMax);

            // TODO JUNCAO DA NOVA POPULACAO
            newPop.addAll(restanteList);
            newPop = isMax? newPop.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()):
                             newPop.stream().sorted().collect(Collectors.toList());

            indL.addAll(newPop);

            // TODO CLEAR NAS LISTAS
            newPop.clear();
            joinPop.clear();

            System.out.println(imprimir(getBetterInd(indL),rep));
        }

        return getBetterInd(indL);
    }
    private Individuo getBetterInd(List<Individuo> indL) {
        Individuo ind = null;
        for (int i = 0; i < indL.size(); i++) {
            if(i==0)
                ind = indL.get(i);
            else{
                if(ind.getAvaliacao() > indL.get(i).getAvaliacao())
                    ind = indL.get(i);
            }
        }
        return ind;
    }

    private List<Individuo> geraDescendentes(List<Individuo> indL) {
        List<Individuo> joinPop = new LinkedList<>();
        List<Individuo> listTemp = new LinkedList<>(indL);
        List<Individuo> mutanteList = new LinkedList<>();
        List<Individuo> filhosList = new LinkedList<>();

        shuffle(listTemp);

        while(!listTemp.isEmpty()){
            Individuo pai_1 = listTemp.remove(0);
            Individuo pai_2 = listTemp.remove(0);
            filhosList.addAll(pai_1.getFilhos(pai_2));
            mutanteList.add(pai_1.getMutante());
            mutanteList.add(pai_2.getMutante());

        }

        joinPop.addAll(filhosList);
        joinPop.addAll(mutanteList);

        return joinPop;
    }
    private List<Individuo> elite (List<Individuo> joinPop,int nElite, boolean isMax){

        return isMax ?
                joinPop.stream().sorted(Comparator.reverseOrder()).limit(nElite).collect(Collectors.toList()):
                joinPop.stream().sorted().limit(nElite).collect(Collectors.toList());


    }

    private List<Individuo> rodaRoleta (List<Individuo> joinPop, int nRestantes, boolean isMax){
        List<Individuo> joinPopTemp = new LinkedList<>(joinPop);
        List<Individuo> resultadoRoleta = new LinkedList<>();
        shuffle(joinPopTemp);
        double shift = getBetterInd(joinPop).getAvaliacao();
        if(shift <= 0 )
            shift = Math.abs(shift) + 0.0001;
        else
            shift = 0;
        Individuo ind;
        for (int i = 0; i < nRestantes; i++) {
            ind = roleta(joinPopTemp,isMax,shift);
            resultadoRoleta.add(ind);
            joinPopTemp.remove(ind);
        }
        return  resultadoRoleta;
    }
    private Individuo roleta(List<Individuo> joinPop, boolean isMax , double shift) {

        double somaAvaliacao = somaAvaliacoes(joinPop,isMax,shift);
        double limite = somaAvaliacao * Math.random();
        double aux = 0;
        int col;
        for (col = 0; ((col < joinPop.size()) && (aux < limite)); col++) {
            aux += joinPop.get(col).getAvaliacao() + shift;
        }

        if(col != 0)
            return joinPop.get(col-1);
        else
            return joinPop.get(col);
    }


    private double somaAvaliacoes(List<Individuo> individuos,boolean isMax,double shift) {
        return isMax ?
                individuos.stream().mapToDouble(individuo -> individuo.getAvaliacao() + shift).sum() :
                individuos.stream().mapToDouble(individuo -> (1 / individuo.getAvaliacao() + shift )).sum();
    }
        public static String imprimir(Individuo ind, int nGer){
        DecimalFormat df = new DecimalFormat("#,##0.000000");
        df.setRoundingMode(RoundingMode.DOWN);
        String aux = "";
        aux +="Geracao: " + (nGer+1);
        aux +="       F(X): "+ df.format(ind.avaliacao)+" ";
            aux +="         Genes: [";
            for (int i = 0; i < ind.genes.size(); i++) {
                aux += df.format(ind.genes.get(i));
                if(i< ind.genes.size()-1)
                   aux +=", ";
            }
        aux +="]";
        return aux;
    }

}
