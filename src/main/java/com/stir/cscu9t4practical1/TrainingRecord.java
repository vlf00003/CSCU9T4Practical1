// An implementation of a Training Record as an ArrayList
package com.stir.cscu9t4practical1;




import java.util.*;


public class TrainingRecord {
    private List<Entry> tr;

    public TrainingRecord() {
        tr = new ArrayList<Entry>();
    } //constructor

    // add a record to the list
    public void addEntry(Entry e){
        ListIterator<Entry> iter = tr.listIterator();
        boolean present  = false;
        while (iter.hasNext()) {
            Entry current = iter.next();
            if (current.getName().equals(e.getName()) && current.getDay() == e.getDay() && current.getMonth() == e.getMonth()
                    && current.getYear() == e.getYear()) {
                present = true;
            }
        }
        if (present == true){
            System.out.println("This record already exists");
        }
        if (present == false) {
            tr.add(e);
        }
    } // addClass

    // look up the entry of a given day and month
    public String lookupEntry (int d, int m, int y) {
        ListIterator<Entry> iter = tr.listIterator();
        String result = "No entries found";
        while (iter.hasNext()) {
            Entry current = iter.next();
            if (current.getDay()==d && current.getMonth()==m && current.getYear()==y)
                result = current.getEntry();
        }
        return result;
    } // lookupEntry

    // Count the number of entries
    public int getNumberOfEntries(){
        return tr.size();
    }
    // Clear all entries
    public void clearAllEntries(){
        tr.clear();
    }

    public String lookupEntries(int d, int m, int y) {
        ListIterator<Entry> iter = tr.listIterator();
        String result = "";
        while (iter.hasNext()) {
            Entry current = iter.next();
            if (current.getDay()==d && current.getMonth()==m && current.getYear()==y)
                result = result + current.getEntry();
        }
        if(result.equals("")) {
            result = "No entries found";
        }
        return result;
    }
    public String removeEntry(String n, int d, int m, int y){
        ListIterator<Entry> iter = tr.listIterator();
        boolean removed  = false;
        while (iter.hasNext()) {
            Entry current = iter.next();
            if (current.getDay() == d && current.getMonth() == m
                    && current.getYear() == y && current.getName().equals(n)) {
                iter.remove();
                removed = true;
            }
        }
        if (removed == false){
            return "No entry found";
        }
        else {
            return "Record removed";
        }
    } // RemoveClass
} // TrainingRecord