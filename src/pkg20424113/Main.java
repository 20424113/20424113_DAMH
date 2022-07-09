/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20424113;
import java.util.*;
import java.io.*;

/**
 *
 * @author nttthoi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SlangWordList slangwordlist = new SlangWordList();
        HistorySearchList historySearchList = new HistorySearchList();
        int flag = 0;
        do{
            System.out.println("============ FEATURE ============");
            System.out.println("[1] : Show slang words.");
            System.out.println("[2] : Import file slang words.");
            System.out.println("[3] : Search slang word.");
            System.out.println("[4] : Search definition.");
            System.out.println("[5] : History search.");
            System.out.println("[6] : Add slang word.");
            System.out.println("[7] : Edit slang word.");
            System.out.println("[8] : Delete slang word.");
            System.out.println("[9] : Reset list slang words.");
            System.out.println("[10]: Random slang word.");
            System.out.println("[11]: Qiz slang word.");
            System.out.println("[12]: Qiz definition.");
            System.out.println("[13]: Exit");
            System.out.println("=================================");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Choose feature: ");
            String i = scanner.nextLine();
            switch(i){
                case "1":
                    slangwordlist.ShowSlangWordList();
                    continue;
                case "2":
                    slangwordlist.ImportSlangWordList("slang.txt");
                    continue;
                case "3":
                    slangwordlist.SearchKey();
                    continue;
                case "4":
                    slangwordlist.SearchValue();
                    continue;
                case "5":
                    historySearchList.ShowHistorySearchList();
                    continue;
                case "6":
                    continue;
                case "7":
                    continue;
                case "8":
                    continue;
                case "9":
                    continue;
                case "10":
                    continue;
                case "11":
                    continue;
                case "12":
                    continue;
                case "13":
                    flag = 1;
                    break;
                default:
                    flag = 1;
                    break;
            }

        }while(flag != 1);
    }
}
