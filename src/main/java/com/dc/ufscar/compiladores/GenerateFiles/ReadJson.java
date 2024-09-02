package com.dc.ufscar.compiladores.GenerateFiles;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
public class ReadJson {
    private static final String filePath = "src/main/java/com/dc/ufscar/compiladores/GenerateFiles/alimentos.json";

    public static Alimentos readAlimentosFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        Alimentos alimentos = null;
        try {
            JsonNode jsonNode = objectMapper.readTree(new File(filePath));
            List<String> carbs = new ArrayList<String>();
            List<String> prots =new ArrayList<String>();
            List<String> gorduras = new ArrayList<String>();

            jsonNode.get("carboidrato").forEach(node -> carbs.add(node.asText()));
            jsonNode.get("proteina").forEach(node -> prots.add(node.asText()));
            jsonNode.get("gordura").forEach(node -> gorduras.add(node.asText()));


            alimentos = new Alimentos(carbs, prots, gorduras);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return alimentos;
    }
}
