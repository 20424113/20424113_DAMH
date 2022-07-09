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
public class SlangWordList implements Serializable{
    String format = "%1$-20s | %2$-50s";
    TreeMap<String, SlangWord> slangWordList = new TreeMap<>();
    public SlangWordList(){
        try{
            FileInputStream fis = new FileInputStream("slangwords.dat");
            ObjectInputStream iis = new ObjectInputStream(fis);
            slangWordList = (TreeMap<String, SlangWord>) iis.readObject();
        }catch(Exception e){
            System.out.println("Slang Word List Empty!");
        }
    }
    
    private void SaveData(){
        try {
        FileOutputStream fos = new FileOutputStream("slangwords.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(slangWordList);
        } catch (Exception e) {
        }
    }
    public void ImportSlangWordList(){
        String line;
        String splitBy = "`";  
        try   
        {  
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter absolute path file: ");
            String file;
            file = scanner.nextLine();
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
            System.out.println("Import slang words success!");
        }catch (IOException e){
            System.out.println("File doesn't exits!");
        }
        SaveData();
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
        Collection<SlangWord> c = slangWordList.values();
        Iterator<Map.Entry<String, SlangWord>> itr = slangWordList.entrySet().iterator();
        Map.Entry<String, SlangWord> entry;
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
    
    public void SearchKey(HistorySearchList hsl){
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
        Collection<SlangWord> c = slangWordList.values();
        Iterator<Map.Entry<String, SlangWord>> itr = slangWordList.entrySet().iterator();
        Map.Entry<String, SlangWord> entry = null;
        //iterate through TreeMap values iterator
        hsl.AddHistorySearch(searchText);
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
    public void SearchValue(HistorySearchList hsl){
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
        hsl.AddHistorySearch(searchText);
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
        if (slangWordList.get(slangword) != null){
            System.out.println("Are you sure to delete?");
            System.out.println("[1]: Yes");
            System.out.println("[2]: No");
            System.out.print("[Choose:");
            int index = 1;
            while(true){
                try {
                    index = Integer.parseInt(scanner.nextLine());
                    if(index == 1 || index == 2){
                        break;
                    }
                    System.out.print("Invalid input! Re-Enter: ");
                    continue;
                } catch (NumberFormatException e) {
                    System.out.print("Invalid input! Re-Enter: ");
                    continue;
                }
            }
            if(index == 1){
                slangWordList.remove(slangword);
                System.out.println("Delete slang word success!");
                SaveData();
            }
            return true;
        }
        System.out.println("Slang word not found!");
        return false;
    }
    
    public boolean AddSlangWord(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("============ New Slang Word ============");
        SlangWord slw = new SlangWord();
        slw.Create();
        if(slangWordList.get(slw.getSlangWord()) != null){
            System.out.println("Slangword is exists! Do you wanna overwrite or dupicate?");
            System.out.println("[1]: Overwrite");
            System.out.println("[2]: Duplicate");
            System.out.print("[Choose:");
            int index = 1;
            while(true){
                try {
                    index = Integer.parseInt(scanner.nextLine());
                    if(index == 1 || index == 2){
                        break;
                    }
                    System.out.print("Invalid input! Re-Enter: ");
                    continue;
                } catch (NumberFormatException e) {
                    System.out.print("Invalid input! Re-Enter: ");
                    continue;
                }
            }
            switch(index){
                case 1:
                    slangWordList.put(slw.getSlangWord(), slw);
                    break;
                case 2:
                    int time = (int) (new Date().getTime()/1000);
                    slw.SetSlangWord(slw.getSlangWord()+ "_" + time);
                    slangWordList.put(slw.getSlangWord()+ "_" + time, slw);
                    break;
            }
        }else{
            slangWordList.put(slw.getSlangWord(), slw);
        }
        SaveData();
        System.out.println("Add slang word success!");
        return true;
    }
    
    public void ResetSlangWords(){
        slangWordList.clear();
        String line = "";  
        String splitBy = "`";  
        try   
        {  
            BufferedReader br = new BufferedReader(new FileReader("slang.txt"));  
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
        SaveData();
        System.out.println("Reset success!");
        SaveData();
    }
    
    public void RandomSlangWord(){
        List<SlangWord> slangwords = new ArrayList<SlangWord>(slangWordList.values());
        Collections.shuffle(slangwords);
        System.out.println("============ Random Slang Word ============");
        System.out.println(slangwords.get(0).getSlangWord() + " - " + slangwords.get(0).getDefinintions());
    }
    
    public void QizSlangWord(int type){
        List<SlangWord> slangwords = new ArrayList<SlangWord>(slangWordList.values());
        Collections.shuffle(slangwords);
        Random r = new Random();
        int id_key = r.nextInt((3 - 0) + 0) + 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("============== Qiz Slang Word =============");
        if(type == 0){
            System.out.println("What is definition for slang word `"+ slangwords.get(id_key).getSlangWord()+"`?");
        }else{
            System.out.println("What is slang word for definition `"+ slangwords.get(id_key).getDefinintions()+"`?");
        }
        ArrayList<String> stt = new ArrayList();
        String[] strs = {"A", "B", "C", "D"};
        stt.addAll(Arrays.asList(strs));
        for (int i = 0; i < 4; i++) {
            if(type == 0){
                System.out.println("["+stt.get(i)+"]:" + slangwords.get(i).getDefinintions());
            }else{
                System.out.println("["+stt.get(i)+"]:" + slangwords.get(i).getSlangWord());
            }
        }
        System.out.println("===========================================");
        System.out.print("Choose answer: ");
        String str = "";
        while(true){
            try {
                str = scanner.nextLine();
                if(stt.contains(str)){
                    break;
                }
                System.out.print("Invalid input! Re-Enter: ");
                continue;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Re-Enter: ");
                continue;
            }
        }
        if(stt.indexOf(str) == id_key){
            System.out.println("Correct!");
        }else{
            System.out.println("Incorrect!");
            String answer = (type == 0) ? slangwords.get(id_key).getDefinintions(): slangwords.get(id_key).getSlangWord();
            System.out.println("Answer: [" + stt.get(id_key) + "]: " + answer);
        }
    }
    
    public void EditSlangWord(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("============ Edit Slang Word ============");
        System.out.print("Enter slang word you wanna edit: ");
        String slangword = "";
        slangword =scanner.nextLine();
        SlangWord slw = slangWordList.get(slangword);
        if (slw != null){
            System.out.println("[1]: Edit slang word");
            System.out.println("[2]: Edit definition");
            System.out.println("[3]: Edit all");
            System.out.print("[Choose:");
            int index = 1;
            while(true){
                try {
                    index = Integer.parseInt(scanner.nextLine());
                    if(index == 1 || index == 2 || index == 3){
                        break;
                    }
                    System.out.print("Invalid input! Re-Enter: ");
                    continue;
                } catch (NumberFormatException e) {
                    System.out.print("Invalid input! Re-Enter: ");
                    continue;
                }
            }
            String old_slw = slw.getSlangWord();
            switch(index){
                case 1:
                    slw.EditSlangWord();
                    if(slw.getSlangWord() != old_slw){
                        if(slangWordList.get(slw.getSlangWord()) != null){
                            System.out.print("Slang word is exists!");
                        }
                        else{
                            slangWordList.remove(old_slw);
                            slangWordList.put(slw.getSlangWord(), slw);
                            System.out.println("Edit slang word success!");
                        }
                    }
                    break;
                case 2:
                    slw.EditDefinition();
                    slangWordList.put(slw.getSlangWord(), slw);
                    System.out.println("Edit slang word success!");
                    break;
                case 3:
                    slw.Edit();
                    if(slw.getSlangWord() != old_slw){
                        if(slangWordList.get(slw.getSlangWord()) != null){
                            System.out.print("Slang word is exists!");
                        }
                        else{
                            slangWordList.remove(old_slw);
                            slangWordList.put(slw.getSlangWord(), slw);
                            System.out.println("Edit slang word success!");
                        }
                    }
                    break;
                default: 
                    break;
            }
            SaveData();
        }else{
            System.out.println("Slang word doesn't exists!");
        }
    }
}
