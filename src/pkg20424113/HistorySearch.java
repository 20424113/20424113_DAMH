/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20424113;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author nttthoi
 */
public class HistorySearch implements Serializable{
    private String SearchText;
    private LocalDateTime DateSearch;
    
    public HistorySearch(){
        SearchText = "";
        DateSearch = LocalDateTime.now();
    }
    
    public HistorySearch(String searchText){
        SearchText = searchText;
        DateSearch = LocalDateTime.now();
    }
    
    public String getSearchText(){
        return SearchText;
    }
    
    public String getDateSearch(){
        DateTimeFormatter formatDateSearch = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return formatDateSearch.format(DateSearch);
    }
}
