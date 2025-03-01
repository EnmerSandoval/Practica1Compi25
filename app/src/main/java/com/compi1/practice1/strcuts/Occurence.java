package com.compi1.practice1.strcuts;

public class Occurence {
     String operator;
     int column;
     String ocurrence;

     public Occurence(String operator, int column, String ocurrence){
         this.operator = operator;
         this.column = column;
         this.ocurrence = ocurrence;
     }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getOcurrence() {
        return ocurrence;
    }

    public void setOcurrence(String ocurrence) {
        this.ocurrence = ocurrence;
    }
}
