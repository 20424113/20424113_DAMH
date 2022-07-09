/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20424113;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
/**
 *
 * @author nttthoi
 */
public class SlangWordList extends TreeMap {
    TreeMap<String, String> slangWordList = new TreeMap<String, String>();
    public SlangWordList(){
        try{
//            FileInputStream fis = new FileInputStream("slangwordlist.dat");
//            ObjectInputStream iis = new ObjectInputStream(fis);
//            slangWordList = (TreeMap<String, String>) iis.readObject();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void ImportSlangWordList(String file){
        String line = "";  
        String splitBy = "`";  
        try   
        {  
            BufferedReader br = new BufferedReader(new FileReader(file));  
            while ((line = br.readLine()) != null)
            {   
                line = line.substring(1, line.length() - 1);
                String[] employee = line.split(splitBy);
                if(employee.length >=2){
                    slangWordList.put(employee[0], employee[1]);
                }
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }
    public void XuatListHocSinh(){
        String format = "%1$-10s %2$-50s";
        System.out.println("---------------------------------------------------------------------------------------------------");
	System.out.printf(format, "STT", "Ma Hoc Sinh", "Ten Hoc Sinh", "Diem", "Dia Chi", "Ghi Chu", "Hinh Anh");
	System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------");
	
        Collection c = slangWordList.values();
        Iterator<Map.Entry<String, String>> itr = slangWordList.entrySet().iterator();
        Map.Entry<String, String> entry = null;
        //iterate through TreeMap values iterator
        while(itr.hasNext()){
//            System.out.println(itr.next());
            entry = itr.next();
            System.out.format(format, entry.getKey(), entry.getValue());
            System.out.println();
        }
        System.out.println("---------------------------------------------------------------------------------------------------");

    }
    public void SearchKey(String searchText){
        Collection c = slangWordList.values();
        Iterator<Map.Entry<String, String>> itr = slangWordList.entrySet().iterator();
        Map.Entry<String, String> entry = null;
        //iterate through TreeMap values iterator
        searchText = ".*" + searchText + ".*";
        while(itr.hasNext()){
            entry = itr.next();
            if(entry.getKey().matches(searchText)){
                System.out.println(entry.getValue());
            }
        }
    }
    public void SearchValue(String searchText){
        Collection c = slangWordList.values();
        Iterator<Map.Entry<String, String>> itr = slangWordList.entrySet().iterator();
        Map.Entry<String, String> entry = null;
        //iterate through TreeMap values iterator
        searchText = ".*" + searchText + ".*";
        while(itr.hasNext()){
            entry = itr.next();
            if(entry.getValue().matches(searchText)){
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
        }
    }
}
