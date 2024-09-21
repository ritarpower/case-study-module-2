package common;

import model.MemeToken;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IOMemeTokenFile {
    private static final String MEMETOKEN_PATH = "src/data/MemeToken.csv";

    public static void writeMemeTokenToFile(MemeToken memeToken) {
        try {
            FileWriter fileWriter = new FileWriter(MEMETOKEN_PATH, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String data = memeToken.getCode() + "," + memeToken.getSymbol() + "," + memeToken.getName() + "," + memeToken.getPrice() + "," +
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
                bufferedWriter.write(memeTokenList.get(i).getCode() + "," + memeTokenList.get(i).getSymbol() + "," + memeTokenList.get(i).getName() + "," + memeTokenList.get(i).getPrice() + "," +
                        memeTokenList.get(i).getDate() + "," + memeTokenList.get(i).getAmount() + "," + memeTokenList.get(i).getMemeType());
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
                String[] tokenData = data.split(",");
                MemeToken memeToken = new MemeToken(tokenData[0], tokenData[1],tokenData[2], Double.parseDouble(tokenData[3]), LocalDate.parse(tokenData[4]),
                        Double.parseDouble(tokenData[5]),tokenData[6]);
                memeTokens.add(memeToken);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return memeTokens;
    }

    public static MemeToken findMemeTokenByCode(String code) {
        List<MemeToken> memeTokens = readMemeTokenFromFile();
        for (MemeToken memeToken : memeTokens) {
            if (memeToken.getCode().equals(code)) {
                return memeToken;
            }
        }
        return null;
    }

    public static void deleteMemeToken(MemeToken memeToken) {
        List<MemeToken> memeTokens = readMemeTokenFromFile();
        for (int i = 0; i < memeTokens.size(); i++) {
            if (memeTokens.get(i).getCode().equals(memeToken.getCode())) {
                memeTokens.remove(i);
                writeMemeTokenListToFile(memeTokens);
                return;
            }
        }
    }

    public static void editMemeToken(MemeToken memeToken) {
        List<MemeToken> memeTokens = readMemeTokenFromFile();
        for (int i = 0; i < memeTokens.size(); i++) {
            if (memeTokens.get(i).getCode().equals(memeToken.getCode())) {
                memeTokens.set(i, memeToken);
                writeMemeTokenListToFile(memeTokens);
                return;
            }
        }
    }
}
