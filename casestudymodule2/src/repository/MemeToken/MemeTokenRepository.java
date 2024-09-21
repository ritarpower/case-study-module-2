package repository.MemeToken;

import common.IOMemeTokenFile;
import model.MemeToken;
import java.util.List;

public class MemeTokenRepository implements IMemeTokenRepository {
    @Override
    public List<MemeToken> getAll() {
        return IOMemeTokenFile.readMemeTokenFromFile();
    }

    @Override
    public void add(MemeToken memeToken) {
        IOMemeTokenFile.writeMemeTokenToFile(memeToken);
    }

    @Override
    public MemeToken findByCode(String code) {
        return IOMemeTokenFile.findMemeTokenByCode(code);
    }

    @Override
    public void update(MemeToken memeToken) {
        IOMemeTokenFile.editMemeToken(memeToken);
    }

    @Override
    public void delete(MemeToken memeToken) {
        IOMemeTokenFile.deleteMemeToken(memeToken);
    }
}
