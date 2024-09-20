package repository.TechnologyToken;

import common.IOTechTokenFile;
import model.TechnologyToken;
import java.util.List;

public class TechTokenRepository implements ITechTokenRepository{

    @Override
    public List<TechnologyToken> getAll() {
        return IOTechTokenFile.readTechTokenFromFile();
    }

    @Override
    public void add(TechnologyToken technologyToken) {
        IOTechTokenFile.writeTechTokenToFile(technologyToken);
    }

    @Override
    public TechnologyToken findByNumber(int number) {
        return IOTechTokenFile.findTechnologyTokenByNumber(number);
    }

    @Override
    public void update(TechnologyToken technologyToken) {
        IOTechTokenFile.writeTechTokenToFile(technologyToken);
    }

    @Override
    public void delete(TechnologyToken technologyToken) {
        IOTechTokenFile.deleteTechnologyToken(technologyToken);
    }
}
