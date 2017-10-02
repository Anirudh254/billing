/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billing;

/**
 *
 * @author Go
 */
public class NewClass {
    public static void main(String[] args){
        DatabaseParties aa=new DatabaseParties();
        String[] a=aa.fetchDetails(0);
        System.out.println(a[0]);
    }
}
