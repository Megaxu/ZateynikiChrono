package com.example.dontwork;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SampleClass extends Activity {

    private static SimpleDateFormat formatTime = new SimpleDateFormat("m:ss.S");

    public static HashMap<Integer, Date> childrenList = new HashMap<>();

    public static TextView highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_highscore);

        highscore = findViewById(R.id.highscore);

        highscore.setText(getIntent().getExtras().getString("user"));
    }

    public static void addChildrenOne(int number, Date time) {
        Date personalTime = time;
        if (numberInListOne(number)) {
            if (personalTime.after(childrenList.get(number))) {
                MainActivity.informTextOne.setText("Результат не улучшен.");
            } else {
                childrenList.put(number, personalTime);
                MainActivity.informTextOne.setText("Результат улучшен.");
            }
        } else {
            childrenList.put(number, personalTime);
            MainActivity.informTextOne.setText("Финиш " + number);
        }
    }

    public static void addChildrenTwo(int number, Date time) {
        Date personalTime = time;
        if (numberInListTwo(number)) {
            if (personalTime.after(childrenList.get(number))) {
                MainActivity.informTextTwo.setText("Результат не улучшен.");
            } else {
                childrenList.put(number, personalTime);
                MainActivity.informTextTwo.setText("Результат улучшен.");
            }
        } else {
            childrenList.put(number, personalTime);
            MainActivity.informTextTwo.setText("Финиш " + number);
        }
    }

    public static boolean numberInListOne(int input) {
        if (childrenList.containsKey(input)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean numberInListTwo(int input) {
        if (childrenList.containsKey(input)) {
            return true;
        } else {
            return false;
        }
    }

    public static String listChildren(){
        String text = "";
        Map<Integer, String> newMap = sortByValues(childrenList);

        Set set2 = newMap.entrySet();
        Iterator iterator2 = set2.iterator();
        int i = 1;
        while (iterator2.hasNext()) {
            Map.Entry me2 = (Map.Entry) iterator2.next();
            text += i +  ") " + me2.getKey() + " - " + formatTime.format(me2.getValue()) + "\n";
            i++;
        }

        return text;
    }

    private static HashMap sortByValues(HashMap map) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });

        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }
}