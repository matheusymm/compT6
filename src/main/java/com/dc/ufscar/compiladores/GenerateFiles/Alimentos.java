package com.dc.ufscar.compiladores.GenerateFiles;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Alimentos {
    private List<String> carb;
    private List<String> prot;
    private List<String> gord;


   
    public Alimentos(List<String> carb, List<String> prot, List<String> gord) {
        this.carb = carb;
        this.prot = prot;
        this.gord = gord;
    
    }

    public List<String> returnCarb(int numAlimentos){
        List<String> randomCarbList = new ArrayList<>();
        Random random = new Random();

        int i=0;
        while(i < numAlimentos){
            int randomIndex = random.nextInt(carb.size());
            String randomCarb = carb.get(randomIndex);
            if (!randomCarbList.contains(randomCarb)) {
                randomCarbList.add(randomCarb);
                i++;
            }
        }
        return randomCarbList;
    }

    public List<String> returnProt(int numAlimentos){
        List<String> randomProtList = new ArrayList<>();
        Random random = new Random();

        int i=0;
        while(i < numAlimentos){
            int randomIndex = random.nextInt(prot.size());
            String randomProt = prot.get(randomIndex);
            if (!randomProtList.contains(randomProt)) {
                randomProtList.add(randomProt);
                i++;
            }
        }
        return randomProtList;
    }
    public List<String> returnGord(int numAlimentos){
        List<String> randomGordList = new ArrayList<>();
        Random random = new Random();

        int i=0;
        while(i < numAlimentos){
            int randomIndex = random.nextInt(gord.size());
            String randomGord = gord.get(randomIndex);
            if (!randomGordList.contains(randomGord)) {
                randomGordList.add(randomGord);
                i++;
            }
        }
        return randomGordList;
    }
    // Getters and Setters
    public List<String> getCarb() {
        return carb;
    }

    public void setCarb(List<String> carb) {
        this.carb = carb;
    }

    public List<String> getProt() {
        return prot;
    }

    public void setProt(List<String> prot) {
        this.prot = prot;
    }

    public List<String> getGord() {
        return gord;
    }

    public void setGord(List<String> gord) {
        this.gord = gord;
    }


    @Override
    public String toString() {
        return "Alimentos{\n" +
                "carboidratos = " + carb +
                "\n, proteinas = " + prot +
                "\n, gordura = " + gord +
                '}';
    }
}
