/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20424113;
import java.io.*;
import java.util.*;
/**
 *
 * @author nttthoi
 */
public class SlangWordList{
    String format = "%1$-20s | %2$-50s";
    TreeMap<String, SlangWord> slangWordList = new TreeMap<>();
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
                    SlangWord slw = new SlangWord(employee[0], employee[1]);
                    slangWordList.put(employee[0],slw);
                }
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }
    private void ShowHeader(){
        System.out.println("---------------------------------------------------------------------------------------------------");
	System.out.printf(format, "Slang word", "Defineitions");
	System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------");
    }
    private void ShowBody(Map.Entry<String, SlangWord> entry){
        System.out.format(format, entry.getKey(), entry.getValue().getDefinintions());
        System.out.println();
    }
    private void ShowFooter(){
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.println();
    }
    public void ShowSlangWordList(){
        System.out.println("============ Show Slang Words ============");
        ShowHeader();
        Collection c = slangWordList.values();
        Iterator<Map.Entry<String, SlangWord>> itr = slangWordList.entrySet().iterator();
        Map.Entry<String, SlangWord> entry = null;
        if(slangWordList.isEmpty()){
            System.out.println("No data to display.");
        }else{
            while(itr.hasNext()){
                entry = itr.next();
                ShowBody(entry);
            }
        }
        ShowFooter();
    }
    
    public void SearchKey(){
        System.out.println("============ Search Slang Word ============");
        String searchText = "";
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter slang word you wanna search: ");
        while(true){
            searchText = scanner.nextLine();
            if(searchText != ""){
                break;
            }
            System.out.print("Please enter slang word! Re-Enter: ");
            continue;
        }
        Collection c = slangWordList.values();
        Iterator<Map.Entry<String, SlangWord>> itr = slangWordList.entrySet().iterator();
        Map.Entry<String, SlangWord> entry = null;
        //iterate through TreeMap values iterator
        HistorySearchList htsl = new HistorySearchList();
        htsl.AddHistorySearch(searchText);
        searchText = ".*" + searchText + ".*";
        int count = 0;
        ShowHeader();
        while(itr.hasNext()){
            entry = itr.next();
            if(entry.getKey().matches(searchText)){
                count += 1;
                ShowBody(entry);
            }
        }
        if(count <=0){
            System.out.println("No match was found for your search!");
        }
        ShowFooter();
    }
    public void SearchValue(){
        System.out.println("============ Search Definition ============");
        String searchText = "";
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter definitions you wanna search: ");
        while(true){
            searchText = scanner.nextLine();
            if(searchText != ""){
                break;
            }
            System.out.print("Please enter definitions! Re-Enter: ");
            continue;
        }
        Collection c = slangWordList.values();
        Iterator<Map.Entry<String, SlangWord>> itr = slangWordList.entrySet().iterator();
        Map.Entry<String, SlangWord> entry = null;
        HistorySearchList htsl = new HistorySearchList();
        htsl.AddHistorySearch(searchText);
        searchText = ".*" + searchText + ".*";
        int count = 0;
        ShowHeader();
        while(itr.hasNext()){
            entry = itr.next();
            if(entry.getValue().SearchDefinition(searchText)){
                count += 1;
                ShowBody(entry);
            }
        }
        if(count <=0){
            System.out.println("No match was found for your search!");
        }
        ShowFooter();
    }
    
    public boolean DeleteSlangWord(){
        System.out.println("============ Delete Slang Word ============");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter slang word you wanna delete: ");
        String slangword = "";
        slangword =scanner.nextLine();
        if (slangWordList.remove(slangword) != null){
            System.out.println("Delete slang word success!");
            return true;
        }
        System.out.println("Slang word not found!");
        return false;
    }
    
    public boolean AddSlangWord(String slangword, String definitions){
        Scanner scanner = new Scanner(System.in);
        System.out.print("============ New Slang Word ============");
        SlangWord slw = new SlangWord();
        slw.Create();
        if(slangWordList.get(slw.getSlangWord()) != null){
            System.out.print("Slangword is exists! Do you wanna overwrite or dupicate?");
            System.out.print("[1]: Overwrite");
            System.out.print("[2]: Duplicate");
            int index = 0;
            while(true){
                try {
                    index = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("Invalid input! Re-Enter: ");
                    continue;
                }
            }
        }
        return true;
    }
}
