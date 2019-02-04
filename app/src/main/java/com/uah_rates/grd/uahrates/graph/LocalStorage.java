package com.uah_rates.grd.uahrates.graph;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uah_rates.grd.uahrates.App;
import com.uah_rates.grd.uahrates.Invariance;
import com.uah_rates.grd.uahrates.api.ApiService;
import com.uah_rates.grd.uahrates.model.pojo.Rate;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import java.util.*;

import static android.content.Context.MODE_PRIVATE;


public class LocalStorage {

    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();

//    final static String KEY_VALUE = "MyVariables";
//    public final static String SP_STORAGE_KEY = "CHART_DATA";

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

    private ApiService service;



    //------------------------Constructor--------------------------------------
    public LocalStorage(Context context) {
        this.context = context;
        service = App.RetrofitClientInstance
                .getRetrofitInstance()
                .create(ApiService.class);

        observables = null;
        observables = new ArrayList<Observable<List<Rate>>>();
        //----------------just show ---------------------
//        for (Map.Entry entry : hashMap10.entrySet()) {
//            System.out.println("Key: " + entry.getKey() + " Value: "
//                    + entry.getValue());
//            Log.e(LOG_TAG, "i =======::========:::for (Map.Entry entry : hashMap10.entrySet())  :::::> " + "Key: " + entry.getKey() + " Value: "
//                    + entry.getValue());
//        }
        //-------------------------------------------------

        getHistory();

    }
    //---------------------------------------------------------------
    public List<Rate> getHistory() {

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
         getRepository();
        //****************************************

        //******************test**********************
       // showHistory(getData(SP_STORAGE_KEY));
        //****************************************

        //----------------just show ---------------------
//        int a = 1;
//        for (Observable<List<Rate>> temp : observables) {
//            System.out.println(temp);
//            Log.d(LOG_TAG, "observables ========= " + temp + " ********* ==" + a);
//            a++;
//        }
        //--------------------------------------------

        return null;
    }

    private Integer getCurrentDate() {

        Calendar calendar = Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);

        String toDay;
        String toMonth;
        if (mDay <10) {
            toDay = "0" + mDay;
        } else {
            toDay = String.valueOf(mDay);
        }
        if (month<10) {
            toMonth = "0"+month;
        } else {
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
        //Метод concat() принимает несколько «наблюдаемых(Observable)» данных и присоединяет их к последовательности.
        //Порядок исходных «наблюдаемых» в concat() данных, имеют значения, и их можно проверить одно за другим.
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
                        Log.d(LOG_TAG, " Observable.concat >>>>>>> onNext " + value.size());
                        linkedHashMap.put(count, value);
                        count++;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(LOG_TAG, " onError " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        saveHashMap(Invariance.SP_ITEM_KEY, linkedHashMap);
                    }
                });
    }


    /**
     * Save  HashMap in SharedPreference
     */
    public void saveHashMap(String key, Object obj) {

        SharedPreferences prefs = context.getApplicationContext().getSharedPreferences(Invariance.SP_STORAGE_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson((LinkedHashMap)obj);
        editor.putString(key, json);
        editor.apply();
    }



}
