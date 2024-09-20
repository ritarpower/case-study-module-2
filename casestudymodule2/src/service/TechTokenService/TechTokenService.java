package service.TechTokenService;

import model.TechnologyToken;
import repository.TechnologyToken.ITechTokenRepository;
import repository.TechnologyToken.TechTokenRepository;

import java.util.List;

public class TechTokenService implements ITechTokenService {
    private ITechTokenRepository techTokenRepository = new TechTokenRepository();
    @Override
    public List<TechnologyToken> getAll() {
        return techTokenRepository.getAll();
    }

    @Override
    public void add(TechnologyToken technologyToken) {
        techTokenRepository.add(technologyToken);
    }

    @Override
    public TechnologyToken findByNumber(int number) {
        return techTokenRepository.findByNumber(number);
    }

    @Override
    public void update(TechnologyToken technologyToken) {
        techTokenRepository.update(technologyToken);
    }

    @Override
    public void delete(TechnologyToken technologyToken) {
        techTokenRepository.delete(technologyToken);
    }
}