package com.netcracker.purchases.models.types;

public class Purchase {
    private static int ID = 0;
    private final int idLocal;
    private String name;
    private int count;
    private String unit;
    private String comment;

    public Purchase(String name, int count, String unit, String comment) {
        this.idLocal = ID;
        this.name = name;
        this.count = count;
        this.unit = unit;
        this.comment = comment;
        ID++;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public String getUnit() {
        return unit;
    }

    public String getComment() {
        return comment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
