package common;

import model.MemeToken;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IOMemeTokenFile {
    private static final String MEMETOKEN_PATH = "src/data/MemeToken.csv";

    public static void writeMemeTokenToFile(MemeToken memeToken) {
        try {
            FileWriter fileWriter = new FileWriter(MEMETOKEN_PATH, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String data = memeToken.getNumber() + "," + memeToken.getSymbol() + "," + memeToken.getName() + "," + memeToken.getPrice() + "," +
                    memeToken.getDate() + "," + memeToken.getAmount() + "," + memeToken.getMemeType();
            bufferedWriter.write(data);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeMemeTokenListToFile(List<MemeToken> memeTokenList) {
        try {
            FileWriter fileWriter = new FileWriter(MEMETOKEN_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < memeTokenList.size(); i++) {
                String data = memeTokenList.get(i).getNumber() + "," + memeTokenList.get(i).getSymbol() + "," + memeTokenList.get(i).getName() + "," + memeTokenList.get(i).getPrice() + "," +
                        memeTokenList.get(i).getDate() + "," + memeTokenList.get(i).getAmount() + "," + memeTokenList.get(i).getMemeType();
                bufferedWriter.write(data);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<MemeToken> readMemeTokenFromFile() {
        List<MemeToken> memeTokens = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(MEMETOKEN_PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (true) {
                String data = bufferedReader.readLine();
                if (data == null) {
                    break;
                }
                String[] tokens = data.split(",");
                MemeToken memeToken = new MemeToken(Integer.parseInt(tokens[0]), tokens[1], tokens[2], Double.parseDouble(tokens[3]), LocalDate.parse(tokens[4]), Double.parseDouble(tokens[5]), tokens[6]);
                memeTokens.add(memeToken);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return memeTokens;
    }

    public static MemeToken findMemeTokenByNumber(int number) {
        List<MemeToken> memeTokens = readMemeTokenFromFile();
        for (MemeToken memeToken : memeTokens) {
            if (memeToken.getNumber() == number) {
                return memeToken;
            }
        }
        return null;
    }

    public static void deleteMemeToken(MemeToken memeToken) {
        List<MemeToken> memeTokens = readMemeTokenFromFile();
        for (MemeToken m : memeTokens) {
            if (m.getNumber() == memeToken.getNumber()) {
                memeTokens.remove(memeToken);
            }
        }
        writeMemeTokenListToFile(memeTokens);
    }

    public static void editMemeToken(MemeToken memeToken) {
        List<MemeToken> memeTokens = readMemeTokenFromFile();
        for (int i = 0; i < memeTokens.size(); i++) {
            if (memeTokens.get(i).getNumber() == memeToken.getNumber()) {
                memeTokens.set(i, memeToken);
                writeMemeTokenToFile(memeToken);
                return;
            }
        }
    }
}
