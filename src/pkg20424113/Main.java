/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20424113;

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
        System.out.println("Initial Mappings are: 1");
        slangwordlist.ImportSlangWordList("slang.txt");
//        slangwordlist.XuatListHocSinh();
        slangwordlist.SearchValue("Initial");
    }
}
