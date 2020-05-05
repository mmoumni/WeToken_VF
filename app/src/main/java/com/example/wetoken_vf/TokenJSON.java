package com.example.wetoken_vf;


public class TokenJSON {

    //@SerializedName("nom")
    private String Nom;

    //@SerializedName("site_web")
    private String Currency;

    //@SerializedName("telephone")
    private String Value;

    //@SerializedName("image")
    private String Type;

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
