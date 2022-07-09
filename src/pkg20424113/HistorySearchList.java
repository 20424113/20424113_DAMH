/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20424113;

import java.io.*;
import java.util.ArrayList;
/**
 *
 * @author nttthoi
 */
public class HistorySearchList implements Serializable{
    String format = "%1$-30s | %2$-50s";
    ArrayList<HistorySearch> hisitorySearchList = new ArrayList<>();
    public HistorySearchList(){
        try{
            FileInputStream fis = new FileInputStream("historysearchs.dat");
            ObjectInputStream iis = new ObjectInputStream(fis);
            hisitorySearchList = (ArrayList<HistorySearch>) iis.readObject();
        }catch(Exception e){
            System.out.println("History Search List Empty!");
        }
    }

    private void SaveData(){
        try {
        FileOutputStream fos = new FileOutputStream("historysearchs.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(hisitorySearchList);
        } catch (Exception e) {
            System.out.println("Save Data Error!");
        }
    }

    public void AddHistorySearch(String searchtext){
        HistorySearch hs= new HistorySearch(searchtext);
        hisitorySearchList.add(0, hs);
        SaveData();
    }

    private void ShowHeader(){
        System.out.println("----------------------------------------------------------------------------------");
	System.out.printf(format, "Search Text", "Date Search");
	System.out.println();
        System.out.println("----------------------------------------------------------------------------------");
    }

    private void ShowBody(HistorySearch hs){
        System.out.format(format, hs.getSearchText(), hs.getDateSearch());
        System.out.println();
    }

    private void ShowFooter(){
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println();
    }

    public void ShowHistorySearchList(){
        System.out.println("============ Show History Search ============");
        ShowHeader();
        for (HistorySearch historySearch : hisitorySearchList) {
            ShowBody(historySearch);
        }
        ShowFooter();
    }
}
