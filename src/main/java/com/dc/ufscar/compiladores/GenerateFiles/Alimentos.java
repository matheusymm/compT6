package com.dc.ufscar.compiladores.GenerateFiles;
import java.util.List;

public class Alimentos {
    private List<String> carb;
    private List<String> prot;
    private List<String> gord;


   
    public Alimentos(List<String> carb, List<String> prot, List<String> gord) {
        this.carb = carb;
        this.prot = prot;
        this.gord = gord;
    
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
