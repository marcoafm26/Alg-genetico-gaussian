package domain;

import domain.factory.IndividuoFactory;
import domain.impl.IndividuoPermFunction;

import java.util.*;
import java.util.stream.Collectors;
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
            List<Individuo> restanteList = this.roleta(joinPop, nPop - nElite, isMax);

            // TODO JUNCAO DA NOVA POPULACAO
            newPop.addAll(restanteList);
            newPop = isMax? newPop.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()):
                             newPop.stream().sorted().collect(Collectors.toList());

            indL.addAll(newPop);

            // TODO CLEAR NAS LISTAS
            newPop.clear();
            joinPop.clear();

            Individuo ind = getBetterInd(indL);
           //System.out.println(imprimir(ind,rep));

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
        List<Individuo> mutanteList = new LinkedList<>();
        List<Individuo> filhosList = new LinkedList<>();

        Random random = new Random();
        Map<Integer,Integer> hash = new HashMap<>();
        List<Individuo> joinPop = new LinkedList<>();
        int rand, nInd = indL.size();


        for (int i = 0; i < nInd; i++) {
            if (hash.get(i) == null) {
                rand = random.nextInt(nInd);
                while (hash.get(rand) != null || rand == i) {
                    rand = random.nextInt(nInd);
                }
                hash.put(rand, rand);
                hash.put(i, i);
                filhosList.addAll(indL.get(i).getFilhos(indL.get(rand)));
                mutanteList.add(indL.get(i).getMutante());
                mutanteList.add(indL.get(rand).getMutante());
            }
        }

        joinPop.addAll(indL);
        joinPop.addAll(filhosList);
        joinPop.addAll(mutanteList);

        return joinPop;
    }
    private List<Individuo> elite (List<Individuo> joinPop,int nElite, boolean isMax){
        return isMax ?joinPop.stream().sorted(Comparator.reverseOrder()).limit(nElite).collect(Collectors.toList()):
                      joinPop.stream().sorted().limit(nElite).collect(Collectors.toList());
    }
    private List<Individuo> roleta(List<Individuo> joinPop, int nRestantes, boolean isMax) {
        List<Individuo> plebe = new LinkedList<>();
        List<Individuo> joinPopTemp = new LinkedList<>(joinPop);
        for (int i = 0; i < nRestantes; i++) {
            double somaAvaliacao = isMax ?
                    joinPopTemp.stream().mapToDouble(individuo -> 1 / individuo.getAvaliacao()).sum():
                    joinPopTemp.stream().mapToDouble(Individuo::getAvaliacao).sum() ;

            double limite = somaAvaliacao * Math.random();
            double aux=0;
            int col;
            for (col = 0; ((col < joinPopTemp.size()) && (aux < limite)); col++) {
                aux += joinPopTemp.get(col).getAvaliacao();
            }

            if(col != 0)
            plebe.add(joinPopTemp.remove(col-1));
            else
            plebe.add(joinPopTemp.remove(col));
        }

        return plebe;
    }

    public static String imprimir(Individuo ind, int nGer){
        String aux = "";
        aux += nGer;
        aux +="  F(X): "+ ind.avaliacao+" ";
        aux +="Genes: " + ind.getGenes().toString();
        return aux;
    }

}
