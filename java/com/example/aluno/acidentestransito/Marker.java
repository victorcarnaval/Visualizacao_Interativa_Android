package com.example.aluno.acidentestransito;

/**
 * Created by Aluno on 19/06/2017.
 */
public class Marker {
    private String endereco;
    private int totalAcidentes;
    private int acidentesFatais;
    private Double latitude;
    private Double longitude;

    public Marker(String end, int ta, int af, Double lat, Double lon){
        endereco = end;
        totalAcidentes = ta;
        acidentesFatais = af;
        latitude = lat;
        longitude = lon;
    }

    public String getEndereco(){
        return endereco;
    }

    public int getTotalAcidentes(){
        return totalAcidentes;
    }

    public int getAcidentesFatais(){
        return acidentesFatais;
    }

    public Double getLatitude(){
        return latitude;
    }

    public Double getLongitude(){
        return longitude;
    }
}
