package domain.factory;

import domain.Individuo;
import domain.impl.IndividuoCrossInTray;
import domain.impl.IndividuoPermFunction;


public class IndividuoCrossInTrayFactory implements IndividuoFactory {

    private final int nRainhas;

    public double chanceMutacao;

    public double domain_x;

    public double domain_y;

    @Override
    public Individuo getIndividuo() {
        return new IndividuoCrossInTray(nRainhas,domain_x,domain_y,chanceMutacao);
    }

    public IndividuoCrossInTrayFactory(int nRainhas, double domain_x, double domain_y, double chanceMutacao) {
        this.nRainhas = nRainhas;
        this.domain_x = domain_x;
        this.domain_y = domain_y;
        this.chanceMutacao = chanceMutacao;
    }
}
