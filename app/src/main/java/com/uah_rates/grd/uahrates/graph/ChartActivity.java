package com.uah_rates.grd.uahrates.graph;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uah_chart.zenolab.chart.LineChartView;
import com.uah_rates.grd.uahrates.Invariance;
import com.uah_rates.grd.uahrates.R;
import com.uah_rates.grd.uahrates.model.pojo.Rate;
import com.uah_rates.grd.uahrates.ui.BaseActivity;

import java.util.*;

public class ChartActivity extends BaseActivity {

    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();

    private Context applicationContext;

    private LinkedHashMap<Integer, List<Rate>> storageLinkedHashMap = new LinkedHashMap<Integer, List<Rate>>();
    private List<Rate> dummyList = new ArrayList<>();

    private LinkedList<Float> outputList = new LinkedList<>();
    private int selected;
    private float chartRange[] = new float[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        applicationContext =getApplicationContext();

        Intent intent = getIntent();
        this.selected = intent.getIntExtra("rcode",0);

        setTitle("Chart");

        storageLinkedHashMap = getHistory(Invariance.SP_ITEM_KEY);
        getData(storageLinkedHashMap );

        LineChartView lineChart = (LineChartView) findViewById(R.id.linechart);
        lineChart.setChartData(chartRange);
    }


    public LinkedHashMap<Integer, List<Rate>> getHistory(String key) {
        SharedPreferences prefs = applicationContext.getSharedPreferences(Invariance.SP_STORAGE_KEY, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(key, "");

        java.lang.reflect.Type type = new TypeToken<LinkedHashMap<Integer, List<Rate> > >(){

            //--------------- TypeToken -----------------------------------------------------
            // TypeToken<T>. С его помощью мы можем получить нужный нам параметризованный тип
            // https://habr.com/ru/company/naumen/blog/228279/
            //TypeToken - Заставляет клиентов создавать подкласс этого класса,
            // который позволяет извлекать информацию о типе даже во время выполнения.

            //        // Uses Reflection api for instantiate anyone(whatever)  object in runtime  from GSON
        // TypeToken -Заставляет клиентов создавать подкласс этого класса, который позволяет извлекать информацию о типе даже во время выполнения.
        // TypeToken - Создает литерал (фиксированное значение) нового типа. Производные, представляемые классом из типа
        // Клиенты создают пустой подкласс. Это создает тип в иерархии типов анонимного класса,
        // чтобы можно было его восстановить во время выполнения, несмотря на стирание Generic.
        // Где ожидается имя типа.
        // new TypeToken<ArrayList<T>>()

            /**
             Создает новый тип литерала. Извлекает представленный класс из типа
                 * параметр.
                 *
                 * <p> Клиенты создают пустой анонимный подкласс. При этом встраивает тип
                 * параметр в иерархии типов анонимного класса, чтобы мы могли восстановить его
                 * во время выполнения несмотря на стирание.

             */

        }.getType();
        LinkedHashMap<Integer, List<Rate>> listLinkedHashMap = gson.fromJson(json, type);
        return listLinkedHashMap;
    }

    private void getData(Map<Integer,List<Rate>> linkedHashMap) {

        outputList.clear();

        for (Map.Entry<Integer, List<Rate>> entry : linkedHashMap.entrySet()) {
            dummyList = entry.getValue();
            for (Rate tickerCode : dummyList) {
                if (tickerCode.getR030() == selected) {
                    outputList.addLast(tickerCode.getRate());
                }
            }
        }
        getChart(outputList);
    }

    public void getChart(LinkedList<Float> valuesChart) {

            Iterator lit = valuesChart.descendingIterator();

            int step = 0;
            while (lit.hasNext()) {

                if (step<chartRange.length) {
                    chartRange[step] = (float) lit.next();
                    step = step + 1;
                }else {
                    break;
                }
            }

            if(selected==643||selected==960){
                Toast.makeText(this, " К сожалению нет корректных данных по данному тикеру!", Toast.LENGTH_LONG).show();
            }
    }

}
