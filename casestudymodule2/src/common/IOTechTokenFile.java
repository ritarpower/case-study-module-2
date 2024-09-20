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
            FileWriter fileWriter = new FileWriter(TECHTOKEN_PATH,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String data = token.getNumber() + token.getSymbol() + token.getName() + token.getPrice() + token.getDate() +
                    token.getAmount() + token.getTechnologyType();
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
                String data = tokens.get(i).getNumber() + tokens.get(i).getSymbol() + tokens.get(i).getName() +
                        tokens.get(i).getPrice() + tokens.get(i).getDate() + tokens.get(i).getAmount() + tokens.get(i).getTechnologyType();
                bufferedWriter.write(data);
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
            while(true){
                String data = bufferedReader.readLine();
                if(data == null){
                    break;
                }
                String[] token = data.split(",");
                TechnologyToken technologyToken = new TechnologyToken(Integer.parseInt(token[0]),token[1],token[2],Double.parseDouble(token[3]),
                        LocalDate.parse(token[4]),Double.parseDouble(token[5]),token[6]);
                tokens.add(technologyToken);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tokens;
    }

    public static TechnologyToken findTechnologyTokenByNumber(int number) {
        List<TechnologyToken> tokens = readTechTokenFromFile();
        for (TechnologyToken token : tokens) {
            if(token.getNumber() == number){
                return token;
            }
        }
        return null;
    }

    public static void deleteTechnologyToken(TechnologyToken technologyToken) {
        List<TechnologyToken> tokens = readTechTokenFromFile();
        for (TechnologyToken token : tokens) {
            if(token.getNumber() == technologyToken.getNumber()){
                tokens.remove(token);
            }
        }
    }

    public static void editTechnologyToken(TechnologyToken token) {
        List<TechnologyToken> tokens = readTechTokenFromFile();
        for (int i = 0; i < tokens.size(); i++) {
            if(tokens.get(i).getNumber() == token.getNumber()){
                tokens.set(i, token);
                writeTechTokenListToFile(tokens);
            }
        }
    }
}
