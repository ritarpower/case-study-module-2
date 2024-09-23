package common;

import model.TechnologyToken;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IOTechTokenFile {
    private static final String TECHTOKEN_PATH = "src/data/TechnologyToken.csv";

    public static void writeTechTokenToFile(TechnologyToken token) {
        try {
            FileWriter fileWriter = new FileWriter(TECHTOKEN_PATH, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String data = token.getCode() + "," + token.getSymbol() + "," + token.getName() + "," + token.getPrice() + "," + token.getDate() + "," +
                    token.getAmount() + "," + token.getTechnologyType();
            bufferedWriter.write(data);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeTechTokenListToFile(List<TechnologyToken> tokens) {
        try {
            FileWriter fileWriter = new FileWriter(TECHTOKEN_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < tokens.size(); i++) {
                bufferedWriter.write(tokens.get(i).getCode() + "," + tokens.get(i).getSymbol() + "," + tokens.get(i).getName() + "," +
                        tokens.get(i).getPrice() + "," + tokens.get(i).getDate() + "," + tokens.get(i).getAmount() + "," + tokens.get(i).getTechnologyType());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<TechnologyToken> readTechTokenFromFile() {
        List<TechnologyToken> tokens = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(TECHTOKEN_PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (true) {
                String data = bufferedReader.readLine();
                if (data == null) {
                    break;
                }
                String[] tokenData = data.split(",");
                TechnologyToken technologyToken = new TechnologyToken(tokenData[0], tokenData[1], tokenData[2], Double.parseDouble(tokenData[3]),
                        LocalDate.parse(tokenData[4]), Double.parseDouble(tokenData[5]), tokenData[6]);
                tokens.add(technologyToken);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tokens;
    }

    public static TechnologyToken findTechnologyTokenByCode(String code) {
        List<TechnologyToken> tokens = readTechTokenFromFile();
        for (TechnologyToken token : tokens) {
            if (token.getCode().equals(code)) {
                return token;
            }
        }
        return null;
    }

    public static void deleteTechnologyToken(TechnologyToken technologyToken) {
        List<TechnologyToken> tokens = readTechTokenFromFile();
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).getCode().equals(technologyToken.getCode())) {
                tokens.remove(i);
                writeTechTokenListToFile(tokens);
                return;
            }
        }
    }

    public static void editTechnologyToken(TechnologyToken technologyToken) {
        List<TechnologyToken> tokens = readTechTokenFromFile();
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).getCode().equals(technologyToken.getCode())) {
                tokens.set(i, technologyToken);
                writeTechTokenListToFile(tokens);
                return;
            }
        }
    }
}
