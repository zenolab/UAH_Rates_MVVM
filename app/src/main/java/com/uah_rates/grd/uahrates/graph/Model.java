package com.uah_rates.grd.uahrates.graph;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uah_rates.grd.uahrates.App;
import com.uah_rates.grd.uahrates.api.RetrofitCallRateService;
import com.uah_rates.grd.uahrates.model.pojo.Rate;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import java.util.*;

import static android.content.Context.MODE_PRIVATE;


public class Model {

    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();

    private Context context;

    private int[] dataRangeArray = new int[10];


    private LinkedList<Float> valueList = new LinkedList<>();
    private  List<Rate> justList = new ArrayList<>();
    private List<Rate> temperList = new ArrayList<>();

    private int selected;


    //Constructs an empty LinkedHashMap instance with the specified initial capacity, load factor and ordering mode.
    //Это создает пустой экземпляр LinkedHashMap, помещенный в вставку, с указанной начальной initial capacity и коэффициентом загрузки(load factor) по умолчанию (0,75).
    //load factor - коэффициент нагрузки определяется как средняя нагрузка, деленная на пиковую нагрузку за указанный период времени.
    /**
     * Parameters:
     * initialCapacity - the initial capacity
     * loadFactor - the load factor
     * accessOrder - the ordering mode - true for access-order, false for insertion-order (accessOrder - режим заказа - true для порядка доступа, false для ввода-заказа)
     */
    private Map<Integer, List<Rate>> linkedHashMap = new LinkedHashMap<Integer, List<Rate>>(10, 0.75f, true);
    public static Map<Integer, List<Rate>> scopeAppLinkedHashMap = new LinkedHashMap<Integer, List<Rate>>(10, 0.75f, true);

    private Map<Integer, io.reactivex.Observable<List<Rate>>> hashMap10 = new HashMap<Integer, io.reactivex.Observable<List<Rate>>>();
    private List<Observable<List<Rate>>> observables;

    private RetrofitCallRateService service;

    public final static String SP_KEY = "CHART_DATA";

    //------------------------Constructor--------------------------------------
    public Model(Context context) {

        this.context = context;

      //  selected = 840;//usd

        service = App.RetrofitClientInstance
                .getRetrofitInstance()
                .create(RetrofitCallRateService.class);

        observables = null;
        observables = new ArrayList<Observable<List<Rate>>>();


        for (Map.Entry entry : hashMap10.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: "
                    + entry.getValue());
            Log.e(LOG_TAG, "i =======::========:::for (Map.Entry entry : hashMap10.entrySet())  :::::> " + "Key: " + entry.getKey() + " Value: "
                    + entry.getValue());
        }

        getRatesС();

    }
    //---------------------------------------------------------------
    public List<Rate> getRatesС() {

        //****************************************
       // showHistory(getHashMap(SP_KEY));
        //****************************************


        int baseDate = getCurrentDate();
        int countdownStep = 10_000;//1 year

        int subtraction = 0; //вычитание

        for (int i = 0; i <= 9; i++) {
            Log.e(LOG_TAG, ":::::::::::::: baseDate ::::::::::::: " + baseDate);
            Log.d(LOG_TAG, "i =============== " + i);
            observables.add(i, service.fetchHistoryObservable(baseDate));
            baseDate -= countdownStep;
            dataRangeArray[i] = baseDate;

        }

        //---------------------------------------
        //****************************************
        // getRepository();
        //****************************************

        int a = 1;
        for (Observable<List<Rate>> temp : observables) {
            System.out.println(temp);
            Log.d(LOG_TAG, "iterator #### ----- temp : observables ========= " + temp + " ********* ==" + a);
            a++;
        }

        return null;
    }

    private Integer getCurrentDate() {

        Calendar calendar = Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);

        String toDay;
        String toMonth;
        if(mDay <10 ) {
            toDay = "0" + mDay;
        }else{
            toDay = String.valueOf(mDay);
        }
        if(month<10){
            toMonth = "0"+month;
        }else {
            toMonth = String.valueOf(month);
        }

        return Integer.parseInt(year+toMonth+toDay);
    }

    private void getRepository() {

        //---------------------------Lambda---------------------------------------------------------------
//        Observable.concat(cacheUsers, dbUsers, networkUsers)
//               // .subscribe(users -> handleResults(users));
//                .subscribe(users -> handleResults(users),
//                        error -> handleError(error)
//                       // ,
//                        // errorThrowable -> handleResultsThrowable(errorThrowable)
//                );
        //------------------------------------------------------------------------------------------
        Observable.concat(Observable.fromIterable(observables))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //DisposableObserver, который автоматически обработает этот четвёртый метод,
                // так что вам достаточно будет позаботиться лишь об уведомлениях от самого Observable.
                .subscribe(new DisposableObserver<List<Rate>>() {
                    int count = 1;
                    LinkedHashMap<Integer, String> hm = new LinkedHashMap<Integer, String>();

                    @Override
                    public void onNext(List<Rate> value) {
                        Log.d(LOG_TAG, ":: Observable.concat >>>>>>> onNext " + value.size());
                        linkedHashMap.put(count, value);

                        count++;
                        Log.d(LOG_TAG, ":: -----count-------(onNext) " + count);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(LOG_TAG, "onError " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                        myResult(linkedHashMap);
                    }
                });

    }

    private void myResult(Map<Integer, List<Rate>> linkedHashMap) {

        saveHashMap(SP_KEY, linkedHashMap);
        // saveHashMap(SP_KEY, null);  //CLEAR STORAGE
        temperList = null;
        showHistory(linkedHashMap);

        Log.e(LOG_TAG, "{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{} ");
        for (Float value : valueList) {
            //                System.out.println(model.getName());
            Log.e(LOG_TAG, ":: -----list- line for chart value------ " + value);
        }
        Log.e(LOG_TAG, "{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{} ");

    }

    private void showHistory(Map<Integer,List<Rate>> linkedHashMap) {

        Log.d(LOG_TAG, "========================= <linkedHashMap > ==========================");

        for (Map.Entry<Integer, List<Rate>> entry : linkedHashMap.entrySet()) {
            Log.d(LOG_TAG, " *** linkedHashMap @-- Map.Entry - это ключ и его значение, объединенное в один класс.  --@********* "
                    + entry.getKey() + " " + entry.getValue());


            temperList = entry.getValue();
            for (Rate tickerCode : temperList) {
                //                System.out.println(tickerCode.getName());
                Log.e(LOG_TAG, ":: -----list- result------ " + tickerCode.getTxt());
                Log.d(LOG_TAG, ":: -----list- result------ " + tickerCode.getExchangedate());
                Log.d(LOG_TAG, ":: -----list- result------ " + tickerCode.getRate());
                Log.d(LOG_TAG, ":: -----list- result------ " + tickerCode.getCc());  // RSD
                Log.d(LOG_TAG, ":: -----list- result------ " + tickerCode.getR030()); // 944

                if (tickerCode.getR030() == selected) {
                    // valueList.addFirst();
                    valueList.addLast(tickerCode.getRate());

                }
            }
            //-------------------------------------------
            /* Display content using Iterator*/
//        Set set = hashMap10.entrySet();
//        Iterator iterator = set.iterator();
//        while(iterator.hasNext()) {
//            Map.Entry mentry = (Map.Entry)iterator.next();
//            System.out.print("............key is: "+ mentry.getKey() + " & Value is: ");
//            System.out.println(mentry.getValue());
//            Log.e(LOG_TAG, "_________while(iterator.hasNext())_____  "  + mentry.getKey() + " & Value is: ");
//
//
//        }
            //-------------------------------------------

        }
    }

    private void handleResultsThrowable(Throwable t) {

        Log.e(LOG_TAG, " Throwable  " + t.getMessage());
    }

    //===============================================================
    private  void handleResults(List<Rate> rates) {
        Log.e(LOG_TAG, "onSuccess " + rates.size());
    }
    private void handleError(Throwable throwable) {
        Log.d(LOG_TAG, "onError " + throwable.getMessage());
    }


    //===============================================================

    //--------------------------------------------------------------------------------------------------

    /**
     * Save and get HashMap in SharedPreference
     */

    public void saveHashMap(String key, Object obj) {
        // getActivity().getApplicationContext().getSharedPreferences("MyVariables", Context.MODE_PRIVATE);
        // SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences("MyVariables", Context.MODE_PRIVATE);
        SharedPreferences prefs = context.getApplicationContext().getSharedPreferences("MyVariables", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }


    public LinkedHashMap<Integer, List<Rate>> getHashMap(String key) {
        // SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences prefs = context.getApplicationContext().getSharedPreferences("MyVariables", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(key, "");
        // Uses Reflection api for instantiate anyone(whatever)  object in runtime  from GSON
        // TypeToken -Заставляет клиентов создавать подкласс этого класса, который позволяет извлекать информацию о типе даже во время выполнения.
        // TypeToken - Создает литерал (фиксированное значение) нового типа. Производные, представляемые классом из типа
        // Клиенты создают пустой подкласс. Это создает тип в иерархии типов анонимного класса,
        // чтобы можно было его восстановить во время выполнения, несмотря на стирание.
        // Где ожидается имя типа.
        // new TypeToken<ArrayList<T>>()
        // public class TypeToken<T>
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


}
