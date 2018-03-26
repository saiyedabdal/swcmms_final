package com.example.smahsanabdal.finalfirstlevel;

/**
 * Created by Ishan Jha on 3/26/2018.
 */
import java.math.BigDecimal;
import java.util.Date;

public class Invoices {
    public e_MB[] getInvoices() {
         e_MB[] data = new e_MB[10];

         for(int i = 0; i < 10; i ++) {
             e_MB row = new e_MB();
            row.e_title="Title";
             data[i] = row;
             }
         return data;

         }
}
