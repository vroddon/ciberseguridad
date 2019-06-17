package oeg.ciberseguridad.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.contains("<http://ont.thomsonreuters.com/mdaas/HeadquartersAddress>"))
                    {
                    //    System.out.print(count+"\r");
                        for(int k =0;k<empresas.size();k++)
                        {
                            String empresa = empresas.get(k);
                            if (line.contains(empresa))
                            {
                                hits.add(line);
                                System.out.println(line);
                            }
                        }
                    }
                    count++;
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
