package com.example.smahsanabdal.finalfirstlevel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TableLayout;
 import android.app.ProgressDialog;
 import android.graphics.Color;
 import android.os.AsyncTask;
 import android.util.TypedValue;
 import android.view.Gravity;
 import android.view.View;
 import android.widget.FrameLayout;
 import android.widget.LinearLayout;
 import android.widget.TableRow;
 import android.widget.TextView;

 import java.math.BigDecimal;
 import java.text.DecimalFormat;
 import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {
    private TableLayout mTableLayout;
    ProgressDialog mProgressBar;
    int leftRowMargin=0;
    int topRowMargin=0;
    int rightRowMargin=0;
    int bottomRowMargin = 0;
    int textSize = 0, smallTextSize =0, mediumTextSize = 0;
    int i,rows;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = new ProgressDialog(this);
         // setup the table
         mTableLayout = (TableLayout)
                findViewById(R.id.tableInvoices);
         mTableLayout.setStretchAllColumns(true);
         startLoadData();
    }
    public void startLoadData() {

         mProgressBar.setCancelable(false);
        mProgressBar.setMessage("Fetching Invoices..");
         mProgressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
         mProgressBar.show();
         new LoadDataTask().execute(0);

         }

             public void loadData() {
         textSize = (int)
                getResources().getDimension(R.dimen.font_size_verysmall);
         smallTextSize = (int)
                getResources().getDimension(R.dimen.font_size_small);
         mediumTextSize = (int)
                getResources().getDimension(R.dimen.font_size_medium);
                 Invoices invoices=new Invoices();
         e_MB[] data = invoices.getInvoices();
         rows = data.length;
         getSupportActionBar().setTitle("Invoices (" +
                String.valueOf(rows) + ")");
         TextView textSpacer = null;

         mTableLayout.removeAllViews();

         // -1 means heading row
         for(i = -1; i < rows; i ++) {
             e_MB row = null;
             if (i > -1)
                 row = data[i];
             else {
                 textSpacer = new TextView(this);
                 textSpacer.setText("");

                 }
             // data columns
              final TextView tv = new TextView(this);
              tv.setLayoutParams(new
                     TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                      TableRow.LayoutParams.WRAP_CONTENT));
             tv.setGravity(Gravity.LEFT);

              tv.setPadding(5, 15, 0, 15);
              if (i == -1) {
                  tv.setText("BOQ Sequence");
                  tv.setBackgroundColor(Color.parseColor("#f0f0f0"));
                  tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                  }
              else {
                  tv.setBackgroundColor(Color.parseColor("#f8f8f8"));
                  tv.setText(String.valueOf(row.emb_b));
                  tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                         textSize);
                  }



             final TableRow tr = new TableRow(this);
              tr.setId(i + 1);
              TableLayout.LayoutParams trParams = new
                      TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                       TableLayout.LayoutParams.WRAP_CONTENT);
              trParams.setMargins(leftRowMargin, topRowMargin,
                     rightRowMargin, bottomRowMargin);
              tr.setPadding(0,0,0,0);
              tr.setLayoutParams(trParams);

                //AddTextView
              tr.addView(tv);

              mTableLayout.addView(tr, trParams);

              if (i > -1) {

                  // add separator row
                  final TableRow trSep = new TableRow(this);
                  TableLayout.LayoutParams trParamsSep = new
                         TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                          TableLayout.LayoutParams.WRAP_CONTENT);
                  trParamsSep.setMargins(leftRowMargin,
                         topRowMargin, rightRowMargin, bottomRowMargin);

                  trSep.setLayoutParams(trParamsSep);
                  TextView tvSep = new TextView(this);
                  TableRow.LayoutParams tvSepLay = new
                         TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                          TableRow.LayoutParams.WRAP_CONTENT);
                  tvSepLay.span = 4;
                  tvSep.setLayoutParams(tvSepLay);
                  tvSep.setBackgroundColor(Color.parseColor("#d9d9d9"));
                          tvSep.setHeight(1);

                  trSep.addView(tvSep);
                  mTableLayout.addView(trSep, trParamsSep);
                  }


              }
                  }
    class LoadDataTask extends AsyncTask<Integer, Integer,
            String> {
         @Override
         protected String doInBackground(Integer... params) {

             try {
                Thread.sleep(2000);

                } catch (InterruptedException e) {
                e.printStackTrace();
                }

             return "Task Completed.";
             }
         @Override
         protected void onPostExecute(String result) {
             mProgressBar.hide();
             loadData();
             }
         @Override
         protected void onPreExecute() {
             }
         @Override
         protected void onProgressUpdate(Integer... values) {

         }
         }
    public void createCol(){

    }

}

