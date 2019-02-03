package com.uah_chart.zenolab.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

import android.util.Log;
import android.view.View;

import java.util.Arrays;
import java.util.Collections;

public class LineChartView extends View {

    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();

    int years = 10;


    private static final float MIN_LINES = 0.0f;
    private static final float MAX_LINES = 10;
    private static final int[] DISTANCES = {1, 2, 5}; //distance for horizontal lines

    //String datesCalculate [] = new String[] { "2008/10/25", "2009/10/25", "2010/10/25", "2011/10/25",  "2012/10/25","2013/10/25","2014/10/25","2015/10/25","2016/10/25","2017/10/25","2018/10/25" };
    // String datesCalculate [] = new String[] { "2009", "2010", "2011",  "2012","2013","2014","2015","2016","2017","2018" };
    // int datesCalculate [] = new int[] { 2009, 2010, 2011,  2012,2013,2014,2015,2016,2017,2018 };
    int datesCalculate[] = new int[10];
    // int datesReverse [] = new int[10];


    private float[] datapoints = new float[]{};
    private Paint paint = new Paint();

    public void setChartData(float[] datapoints) {
        this.datapoints = datapoints.clone();
    }

    /**
     * Это конструктор должен объязательно иметь определенную View внутри xml layout! - иначе приложение failed !!!
     * Если inflate берет разметку не и з кода ,а из xml ,
     * то конструктор принимает всегда два параметра Context и AttributeSet !!!
     */
    public LineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);

        int year = 2018;
        int subtraction = 0; //вычитание
        int temp = 0;

        for (int i = 0; i <= 9; i++) {

            temp = year - i;
            datesCalculate[i] = temp;
            Log.d(LOG_TAG, "i ======YEAR FOR ========= = " + year);

        }

        reverse(datesCalculate);


    }


    public static void reverse(int[] array) {
        if (array == null) {
            return;
        }
        int i = 0;
        int j = array.length - 1;
        int tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }

    }


    private void invertUsingFor(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }

    }

    // Arrays.sort(datesCalculate, Collections.reverseOrder());

    /*
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = 0;
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        if (width == height) {
            size = height;
        } else {
            size = width;
        }
        setMeasuredDimension(size, size);
    }
    */

    @Override
    protected void onDraw(Canvas canvas) {

        drawLineChart(canvas);
        float maxValue = getMax(datapoints);
        drawBackground(canvas, maxValue);
    }

    private void drawBackground(Canvas canvas, float maxValue) {

        int range = getLineDistance(maxValue);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(32);
        paint.setStrokeWidth(1);
        for (int y = 0; y < maxValue; y += range) {

            final int yPos = (int) getYPos(y);

            // turn off anti alias for lines, they get crisper then
            paint.setAntiAlias(false);
            canvas.drawLine(0, yPos, getWidth(), yPos, paint);

            // turn on anti alias again for the text
            paint.setAntiAlias(true);
            canvas.drawText(String.valueOf(y), getPaddingLeft() - 35, yPos - 12, paint);

        }

        for (int x = 0; x < datapoints.length; x++) {

            final int xPos = (int) getXPos(x);

            float startX, stopX, startY, stopY;

            float width = getWidth();

            int height = canvas.getHeight();

            int gridSize = years;

            //Align
            // int gridSpacing = (int) (Math.min(width, height) / gridSize);
            int gridSpacing = (int) (Math.ceil(height) / gridSize);
            int boardSize = gridSize * gridSpacing;

            int yOffset = (height - boardSize);

            startX = xPos;
            startY = yOffset;

            stopX = startX;
            stopY = startY + boardSize;

            paint.setAntiAlias(false);
            canvas.drawLine(startX, startY, stopX, stopY, paint);
            //  canvas.drawLine(startX, getHeight(), stopX, stopY, paint);

            // turn on anti alias again for the text
            paint.setTextSize(16);
            paint.setAntiAlias(true);
            canvas.drawText(String.valueOf(datesCalculate[x]), stopX + 6, stopY - 12, paint);

        }

    }

    //Вычислить количество горизонтальных линий
    private int getLineDistance(float maxValue) {
        int distance;
        int distanceIndex = 0;
        int distanceMultiplier = 1;
        float numberOfLines = MIN_LINES;

        do {
            //For first index element
            distance = DISTANCES[distanceIndex] * distanceMultiplier;
            //Not working with api 23. FloatMath is deprecated. use Math instead. #366
            // numberOfLines = (int) FloatMath.ceil(maxValue / distance); - дает целое число с нулевой дробной частью, ближайшее к числу аргумента справа, другими словами — округляет дробь
            numberOfLines = (float) Math.ceil(maxValue / distance); // получить колистве линий исходня из максимального значения

            distanceIndex++;
            if (distanceIndex == DISTANCES.length) {
                distanceIndex = 0;
                distanceMultiplier *= 10; //
            }

            //число линий не больше и не меньше указанного диапазона above   private static final int[] DISTANCES = { 1, 2, 5 };
        } while (numberOfLines < MIN_LINES || numberOfLines > MAX_LINES);

        return distance;
    }

    //------------------------draw------------------------------------------------------------------

    /**
     * конструируем объект path, используя созданный ранее метод getYPos().
     * Также там используется метод getXPos(), который работает аналогичным образом,
     * только не инвертирует значения.
     * Создание Path начинается с инициализации начальной точкой.
     * Далее мы продлеваем path, добавляя следующие точки.
     */
    private void drawLineChart(Canvas canvas) {
        // Path  -рисовать линии между точками
        Path path = new Path();
        path.moveTo(getXPos(0), getYPos(datapoints[0]));
        for (int i = 1; i < datapoints.length; i++) {
            path.lineTo(getXPos(i), getYPos(datapoints[i]));
        }

        /**
         * График используя  drawPath().
         */
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);
        paint.setColor(0xFFDB166F);
        paint.setAntiAlias(true);
        paint.setShadowLayer(4, 2, 2, 0x80000000);
        canvas.drawPath(path, paint);
        paint.setShadowLayer(0, 0, 0, 0);
    }

    //------------------ calculate max value -------------------------------------------------------

    /**
     * Функция для вычисления максимального значения Y (линии графика) перебирает массив и находит максимум.
     */
    private float getYPos(float value) {
        float height = getHeight() - getPaddingTop() - getPaddingBottom();
        float maxValue = getMax(datapoints);

        // scale it to the view size
        // масштабирования под высоту view
        value = (value / maxValue) * height;

        // invert it so that higher values have lower y
        // инверсия
        value = height - value;

        // offset it to adjust for padding
        // смещение чтобы учесть padding
        value += getPaddingTop();
        return value;
    }


    /**
     * Для X координаты ( линии графика)- функция масштабирования
     */
    private float getXPos(float value) {
        float width = getWidth() - getPaddingLeft() - getPaddingRight();
        float maxValue = datapoints.length - 1;

        // масштабирования под размер view
        value = (value / maxValue) * width;

        // смещение чтобы учесть padding
        value += getPaddingLeft();

        return value;
    }

    private float getMax(float[] array) {
        float max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

}