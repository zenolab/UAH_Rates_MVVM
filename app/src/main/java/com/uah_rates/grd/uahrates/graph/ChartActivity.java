package com.uah_rates.grd.uahrates.graph;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uah_chart.zenolab.chart.LineChartView;
import com.uah_rates.grd.uahrates.R;
import com.uah_rates.grd.uahrates.model.pojo.Rate;

import java.util.*;

public class ChartActivity extends AppCompatActivity {

    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();

    private Context applicationContext;
    private final static String sp_key = "CHART_DATA";


    private LinkedHashMap<Integer, List<Rate>> storageLinkedHashMap = new LinkedHashMap<Integer, List<Rate>>();
    private List<Rate> dummyList = new ArrayList<>();

    private LinkedList<Float> outputList = new LinkedList<>();
    private int selected;
    private float chartRange[] = new float[10];

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        context=this;
        applicationContext =getApplicationContext();

        Intent intent = getIntent();
        selected = intent.getIntExtra("rcode",0);//here 0 is the default value

        //---------------------------------------------
       // selected = 840;//usd
        // selected = 978;//eur
        // selected = 959;//GOLD
        storageLinkedHashMap = getHashMap(sp_key);
        // showHistory();
        Log.d(LOG_TAG, "========================= <linkedHashMap > ==111111111111======================== " +storageLinkedHashMap);

        showHistory(storageLinkedHashMap );
        //---------------------------------------------
        LineChartView lineChart = (LineChartView) findViewById(R.id.linechart);
        lineChart.setChartData(getChartPoints());
    }

    private float[] getChartPoints() {

      // return new float [] {0,0,0,0,0,0,0,0,0,0};
       // return new float [] {1,0,1,0,1,0,1,0,1,0};


       // return new float [] {0,0,0,0,0,0,0,0,0,1}; //wrong
       // return new float [] {0,0,0,0,0,0,0,0,0,3}; //ok
       // return new float [] {999999.34567f,999999,999999,999999,999999,999999,999999,999999,999999,9.99999f}; //ok
          // return new float [] {0,0,0,0,0,0,0,0,9,0};
       // return new float [] {0,0,0,0,0,0,0,0,0,0};
       // return new float [] {0,0,3,4,5,6,7,8,9,0};
       // return new float[] { 10, 12, 7, 14, 15, 19, 13, 2, 10, 13, 13, 10, 15, 14 };

        return chartRange;
    }

    //=====================Load data===========================
    //private void showHistory(LinkedHashMap<Integer,List<Rate>> linkedHashMap) {
    private void showHistory(Map<Integer,List<Rate>> linkedHashMap) {

        Log.d(LOG_TAG, "========================= <linkedHashMap > ========================== " +linkedHashMap);

        // Caused by: java.lang.NullPointerException: Attempt to invoke interface method 'java.util.Set java.util.Map.entrySet()' on a null object reference
        // (16:00-18:00)
        for (Map.Entry<Integer, List<Rate>> entry : linkedHashMap.entrySet()) {
//                            System.out.println(entry.getKey()+" "+entry.getValue());
            Log.d(LOG_TAG, " *** linkedHashMap @-- Map.Entry - это ключ и его значение, объединенное в один класс.  --@********* "
                    + entry.getKey() + " " + entry.getValue());

            dummyList = entry.getValue();
            for (Rate tickerCode : dummyList) {
                //                System.out.println(tickerCode.getName());
                Log.e(LOG_TAG, ":: -----list- result------ " + tickerCode.getTxt());
                Log.d(LOG_TAG, ":: -----list- result------ " + tickerCode.getExchangedate());
                Log.d(LOG_TAG, ":: -----list- result------ " + tickerCode.getRate());
                Log.d(LOG_TAG, ":: -----list- result------ " + tickerCode.getCc());  // RSD
                Log.d(LOG_TAG, ":: -----list- result------ " + tickerCode.getR030()); // 944

                if (tickerCode.getR030() == selected) {
                    // valueList.addFirst();
                    outputList.addLast(tickerCode.getRate());
                }
            }
        }
        showOutput(outputList);
    }

    //-------------------------------------------------------
    public LinkedHashMap<Integer, List<Rate>> getHashMap(String key) {
        SharedPreferences prefs = applicationContext.getSharedPreferences("MyVariables", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(key, "");

       // TypeToken<T>. С его помощью мы можем получить нужный нам параметризованный тип
        // https://habr.com/ru/company/naumen/blog/228279/
        //TypeToken - Заставляет клиентов создавать подкласс этого класса,
        // который позволяет извлекать информацию о типе даже во время выполнения.
        /**
        Создает новый тип литерала. Извлекает представленный класс из типа
    * параметр.
    *
    * <p> Клиенты создают пустой анонимный подкласс. При этом встраивает тип
    * параметр в иерархии типов анонимного класса, чтобы мы могли восстановить его
    * во время выполнения несмотря на стирание.

         */
        java.lang.reflect.Type type = new TypeToken<LinkedHashMap<Integer, List<Rate>>>() {
        }.getType();
        LinkedHashMap<Integer, List<Rate>> obj = gson.fromJson(json, type);
        if(obj ==null){
            Log.d(LOG_TAG, "========================= <linkedHashMap > =>>>>>>>> obj NULL <<<<<<======================== " +obj);
        }else {
            Log.d(LOG_TAG, "========================= <linkedHashMap > ==1000000000======================== " + obj);
        }
        return obj;
    }

    //-------------------------------------------------------


    public void showOutput(LinkedList<Float> valuesChart){

        //temporal resolve for interator NoSuchElementException (several item)
        try {
            Iterator lit = valuesChart.descendingIterator();

            System.out.println("Backward Iterations");
            Log.d(LOG_TAG, "========================= <linkedList > ===========Output===============");
            int step = 0;
            // Caused by: java.util.NoSuchElementException
            // java.lang.ArrayIndexOutOfBoundsException: length=10; index=10
        while(lit.hasNext()){

            chartRange[step] =(float) lit.next();
            step=step+1;
        }

//            for (int i = 0; i < chartRange.length; i++) {
//                System.out.println(chartRange[i]);
//                Log.d(LOG_TAG, "========================= <Array > =====last ticket " + selected + " ======contain=============== " + chartRange[i]);
//
//                chartRange[step] = (float) lit.next(); // java.util.NoSuchElementException
//                step = step + 1;
//            }
        }catch (java.util.NoSuchElementException exception){
            Log.d(LOG_TAG, "===== java.util.NoSuchElementException -- LinkedList<Float>  Iterator is null======");

            Toast.makeText(this, " К сожалению нет точных данных !", Toast.LENGTH_LONG).show();
        }

        //RUB=643
        if(selected==643||selected==960){
            Toast.makeText(this, " К сожалению нет корректных данных по данному тикеру!", Toast.LENGTH_LONG).show();
        }
    }

}
