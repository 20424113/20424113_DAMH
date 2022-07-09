/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20424113;

import java.io.Serializable;
import java.util.Scanner;
/**
 *
 * @author nttthoi
 */
public class SlangWord implements Serializable{
    private String SlangWord;
    private String Definitions;
    public SlangWord(){
        SlangWord = "";
        Definitions = "";
    }
    
    public SlangWord(String slangword, String definitions){
        SlangWord = slangword;
        Definitions = definitions;
    }
    
    public boolean SearchDefinition(String searchText){
        if(Definitions.matches(searchText)){
            return true;
        }
        return false;
    }
    
    public boolean ChangeDefinition(String definitions){
        Definitions = definitions;
        return true;
    }
    
    public boolean ChangeSlangWord(String slangword){
        if(SlangWord.contains(slangword)){
            return false;
        }else{
            SlangWord = slangword;
        }
        return true;
    }
    public boolean AddDefinition(String definitions){
        
        Definitions += definitions;
        return true;
    }
    
    public String getDefinintions(){
        return Definitions;
    }
    
    public void SetDefinintion(String definitions){
        Definitions = definitions;
    }
    
    public String getSlangWord(){
        return SlangWord;
    }
    
    public void SetSlangWord(String slangword){
        SlangWord = slangword;
    }
    
    public void Create(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Slang Word: ");
        SlangWord = scanner.nextLine();
        System.out.print("Enter definitions: ");
        Definitions = scanner.nextLine();
    }
    
    public void Edit(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Slang Word ("+SlangWord+"): ");
        SlangWord = scanner.nextLine();
        System.out.print("Enter definitions ("+Definitions+"): ");
        Definitions = scanner.nextLine();
    }
    public void EditDefinition(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter definitions ("+Definitions+"): ");
        Definitions = scanner.nextLine();
    }
    public void EditSlangWord(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Slang Word ("+SlangWord+"): ");
        SlangWord = scanner.nextLine();
    }
}
