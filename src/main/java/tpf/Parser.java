/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpf;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Joel Caro G
 */
public class Parser {
    
    private ArrayList<Provincia> provinciaArrayList;
    private int provinciaId;
    private String provinciaNombre;
    private Provincia prov;

    public ArrayList<Provincia> parse(String respuesta) {

        provinciaArrayList = new ArrayList<>();
        JSONObject provinciasJson = new JSONObject(respuesta);
        JSONArray provinciasArray = provinciasJson.getJSONArray("provincias");

        for (int i = 0; i < provinciasArray.length(); i++) {
            provinciaId = provinciasArray.getJSONObject(i).getInt("id");
            provinciaNombre = provinciasArray.getJSONObject(i).getString("nombre");

            prov = new Provincia(provinciaId, provinciaNombre);

            provinciaArrayList.add(prov);
        }
        return provinciaArrayList;
    }

    public void recorrerArray() {
        for (Provincia p : provinciaArrayList) {
            System.out.println(p);
        }
    }

    public int totalProvincia() {
        return provinciaArrayList.size();
    }
}
