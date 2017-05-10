package com.bp_sevd.model.production;

/**
 * Created by Martin on 27.04.2017.
 */
public class Investment {

    private double cena;        //cena instalacie
    private FVE_configurations configuration_id;
    private int pocet_panelov;
    private int vykon_baterie;
    private double vykon;       //kWp instalovaneho vykonu
    private double navratnost;     //dni
    private double effectivity;     //efektivita spotreby vyrobenej energie
    private double dotacia;
    private double energy_price;

    public Investment(){}

    public double getDotacia() {
        return dotacia;
    }

    public void setDotacia(double dotacia) {
        this.dotacia = dotacia;
    }

    public double getEnergy_price() {
        return energy_price;
    }

    public void setEnergy_price(double energy_price) {
        this.energy_price = energy_price;
    }

    public int getVykon_baterie() {
        return vykon_baterie;
    }

    public void setVykon_baterie(int vykon_baterie) {
        this.vykon_baterie = vykon_baterie;
    }

    public Investment(FVE_configurations configuration_id){
        this.configuration_id=configuration_id;
    };

    public double getEffectivity() {
        return effectivity;
    }

    public void setEffectivity(double effectivity) {
        this.effectivity = effectivity;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public FVE_configurations getConfiguration_id() {
        return configuration_id;
    }

    public void setConfiguration_id(FVE_configurations configuration_id) {
        this.configuration_id = configuration_id;
    }

    public int getPocet_panelov() {
        return pocet_panelov;
    }

    public void setPocet_panelov(int pocet_panelov) {
        this.pocet_panelov = pocet_panelov;
    }

    public double getVykon() {
        return vykon;
    }

    public void setVykon(double vykon) {
        this.vykon = vykon;
    }

    public double getNavratnost() {
        return navratnost;
    }

    public void setNavratnost(double navratnost) {
        this.navratnost = navratnost;
    }
}
