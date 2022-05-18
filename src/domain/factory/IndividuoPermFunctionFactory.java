package domain.factory;

import domain.Individuo;
import domain.impl.IndividuoPermFunction;


public class IndividuoPermFunctionFactory implements IndividuoFactory {

    private final int nRainhas;

    @Override
    public Individuo getIndividuo() {
        return new IndividuoPermFunction(nRainhas);
    }

    public IndividuoPermFunctionFactory(int nRainhas) {
        this.nRainhas = nRainhas;
    }
}
