package com.example.smahsanabdal.finalfirstlevel;

/**
 * Created by Ishan Jha on 3/26/2018.
 */

import java.math.BigDecimal;
import java.util.Date;
public class amb {
    public int amb_boqseq;
    public String amb_title, amb_status, amb_contractor_comment;
    public BigDecimal amb_sor, amb_units, amb_rate, amb_utd_qty, amb_utd_amt, amb_spb_qty, amb_spb_amt, amb_td_da, amb_td_dra, amb_roy;

    public BigDecimal getAmb_rate() {
        return amb_rate;
    }

    public BigDecimal getAmb_sor() {
        return amb_sor;
    }

    public BigDecimal getAmb_spb_qty() {
        return amb_spb_qty;
    }

    public BigDecimal getAmb_units() {
        return amb_units;
    }

    public BigDecimal getAmb_utd_amt() {
        return amb_utd_amt;
    }
}

