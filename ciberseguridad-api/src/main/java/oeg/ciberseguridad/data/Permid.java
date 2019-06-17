package oeg.ciberseguridad.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static oeg.ciberseguridad.data.CompaniesHouse.sortByValue;

/**
 *
 * @author vroddon
 */
public class Permid {

    public static void main(String[] args) {
        List<String> empresas = new ArrayList();
        try {
            File file = new File("D:\\data\\permid\\data.nt");
            int count = 0;
            int count2 = 0;
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    count++;
                    if (line.contains("4294952721"))
                    {
                        String tokens[]=line.split(" ");
                    //    System.out.println(line);
                        empresas.add(tokens[0].trim());
                        count2++;
                    }
                }
                System.out.println(count + "\t" + count2);
            } catch (Exception e) {
            }
            List<String> hits = new ArrayList();
            count=0;
            Map<String, Integer> mapa = new HashMap();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.contains("<http://ont.thomsonreuters.com/mdaas/HeadquartersAddress>"))
                    {
                        for(int k =0;k<empresas.size();k++)
                        {
                            String empresa = empresas.get(k);
                            if (line.contains(empresa))
                            {
                                try{
                  //              System.out.println(line);
                                    int index = line.indexOf(" ");
                                    int index2 = line.indexOf(" ", index+1);
                                    String pais = line.substring(index2,line.length());
                                    int index3 =  pais.lastIndexOf("\\n");
                                    int index4 = pais.lastIndexOf("\\n", index3-1);
                                    pais = pais.substring(index4+2, index3);
                                    pais = pais.replace("\"", "");
                                    Integer total = mapa.get(pais);
                                    if (total==null)
                                        total =0;
                                    total++;
                                    mapa.put(pais,total);
                                    hits.add(pais);
                                    count++;
                                    System.out.println(count+"\t"+pais);
                                    if (count>=1500)
                                        break;
                                    
                                }catch(Exception ex3)
                                {
                                    
                                }

                            }
                            if (count>=1500)
                                break;                            
                        }
                    }
                    
                }
                
            mapa = sortByValue(mapa);
            ArrayList keyList = new ArrayList(mapa.keySet());
            for (int i = keyList.size() - 1; i >= 0; i--) {
                String key = (String) keyList.get(i);
                Integer value = mapa.get(key);
                System.out.println(key + "\t" + value);
            }
                
                
                
            } catch (Exception e) {
            }            
            for(String hit : hits)
            {
           //     System.out.println(hit);
            }
            
            
        } catch (Exception e2) {

        }
    }
}
