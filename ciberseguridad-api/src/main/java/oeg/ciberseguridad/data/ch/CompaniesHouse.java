package oeg.ciberseguridad.data.ch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * Datos disponibles en
 * https://data.gov.uk/dataset/4462e41a-7413-4359-97f5-420b6ca5f9c0/basic-company-data
 *
 * @author vroddon
 */
public class CompaniesHouse {

    public static void main(String[] args) {
        try {
            File file = new File("D:\\data\\uk-companieshouse\\data.csv");
            int count = 0;
            int count2 = 0;
            Map<String, Integer> mapa = new HashMap();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] s = line.split(",");
                    for (int i = 0; i < s.length; i++) {
                        String si = s[i];
                        //                      System.out.println(i+"\t"+si);
                        if (si.contains("Business and domestic software development")) {
                            count2++;
                            Integer parcial = mapa.get(s[6]);
                            if (parcial == null) {
                                parcial = 0;
                            }
                            parcial++;
                            mapa.put(s[6], parcial);
                            //System.out.println(s[0]+"\t"+s[6]);
                        }
                    }
                    count++;
//                    System.exit(0);
                }
            }
            mapa = sortByValue(mapa);
            int count3 = 0;

            ArrayList keyList = new ArrayList(mapa.keySet());
            for (int i = keyList.size() - 1; i >= 0; i--) {
                String key = (String) keyList.get(i);
                Integer value = mapa.get(key);

//            for (Map.Entry<String, Integer> entry : mapa.entrySet()) {
//                String key = entry.getKey();
                //               Integer value = entry.getValue();
                System.out.println(key + "\t" + value);
                count3++;
                if (count3 == 11) {
                    break;
                }
            }

            System.out.println(count + "\t" + count2);

        } catch (Exception e) {

        }
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}
