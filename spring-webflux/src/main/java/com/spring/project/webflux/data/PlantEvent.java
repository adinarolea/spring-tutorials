package com.spring.project.webflux.data;

import java.util.Date;

public class PlantEvent {

    private Plant plant;
    private Date date;

    public PlantEvent(Plant plant, Date date) {
        this.date = date;
        this.plant = plant;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
