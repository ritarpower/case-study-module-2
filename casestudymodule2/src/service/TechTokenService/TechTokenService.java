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
    public TechnologyToken findByCode(String code) {
        return techTokenRepository.findByCode(code);
    }

    @Override
    public void update(TechnologyToken techToken) {
        techTokenRepository.update(techToken);
    }

    @Override
    public void delete(TechnologyToken techToken) {
        techTokenRepository.delete(techToken);
    }
}