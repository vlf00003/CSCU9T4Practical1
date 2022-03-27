// GUI and main program for the Training Record
package com.stir.cscu9t4practical1;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Number;

public class TrainingRecordGUI extends JFrame implements ActionListener {

    private JTextField name = new JTextField(30);
    private JTextField day = new JTextField(2);
    private JTextField month = new JTextField(2);
    private JTextField year = new JTextField(4);
    private JTextField hours = new JTextField(2);
    private JTextField mins = new JTextField(2);
    private JTextField secs = new JTextField(2);
    private JTextField dist = new JTextField(4);
    private JTextField ter = new JTextField(5);
    private JTextField temp = new JTextField(5);
    private JTextField reps = new JTextField(5);
    private JTextField rec = new JTextField(5);
    private JTextField where = new JTextField(5);
    private JLabel labter = new JLabel(" Cycle - Terrain:");
    private JLabel labtemp = new JLabel(" Cycle - Tempo:");
    private JLabel labreps = new JLabel(" Sprint - Repetitions:");
    private JLabel labrec = new JLabel(" Sprint - Recovery (min):");
    private JLabel labwhere = new JLabel(" Swim - Where:");
    private JLabel labn = new JLabel(" Name:");
    private JLabel labd = new JLabel(" Day:");
    private JLabel labm = new JLabel(" Month:");
    private JLabel laby = new JLabel(" Year:");
    private JLabel labh = new JLabel(" Hours:");
    private JLabel labmm = new JLabel(" Mins:");
    private JLabel labs = new JLabel(" Secs:");
    private JLabel labdist = new JLabel(" Distance (km):");
    private JButton addR = new JButton("Add");
    private JButton lookUpByDate = new JButton("Look Up");
    private JButton findAllByDate = new JButton("Find All By Date");
    private JButton cycle = new JButton("Cycle");
    private JButton sprint = new JButton("Sprint");
    private JButton swim = new JButton("Swim");
    private JButton remove = new JButton("Remove");

    private TrainingRecord myAthletes = new TrainingRecord();

    private JTextArea outputArea = new JTextArea(5, 50);

    public static void main(String[] args) {
        TrainingRecordGUI applic = new TrainingRecordGUI();
    } // main

    // set up the GUI
    public TrainingRecordGUI() {
        super("Training Record");
        setLayout(new FlowLayout());
        add(labn);
        add(name);
        name.setEditable(true);
        add(labd);
        add(day);
        day.setEditable(true);
        add(labm);
        add(month);
        month.setEditable(true);
        add(laby);
        add(year);
        year.setEditable(true);
        add(labh);
        add(hours);
        hours.setEditable(true);
        add(labmm);
        add(mins);
        mins.setEditable(true);
        add(labs);
        add(secs);
        secs.setEditable(true);
        add(labdist);
        add(dist);
        dist.setEditable(true);

        add(labter);
        add(ter);
        ter.setEditable(true);
        add(labtemp);
        add(temp);
        temp.setEditable(true);
        add(labreps);
        add(reps);
        reps.setEditable(true);
        add(labrec);
        add(rec);
        rec.setEditable(true);
        add(labwhere);
        add(where);
        where.setEditable(true);
        add(cycle);
        cycle.addActionListener(this);
        add(sprint);
        sprint.addActionListener(this);
        add(swim);
        swim.addActionListener(this);
        add(remove);
        remove.addActionListener(this);

        add(addR);
        addR.addActionListener(this);
        add(lookUpByDate);
        lookUpByDate.addActionListener(this);
        add(findAllByDate);
        findAllByDate.addActionListener(this);
        add(outputArea);
        outputArea.setEditable(false);
        setSize(720, 200);
        setVisible(true);
        //       blankDisplay();

        // To save typing in new entries while testing, uncomment
        // the following lines (or add your own test cases)

    } // constructor

    // listen for and respond to GUI events
    public void actionPerformed(ActionEvent event) {
        String message = "";
        if (event.getSource() == addR) {
            message = addEntry("generic");
        }
        if (event.getSource() == lookUpByDate) {
            message = lookupEntry();
        }
        if (event.getSource() == findAllByDate) {
            message = findAllByDate();
        }
        if (event.getSource() == cycle) {
            message = addEntry("cycle");
        }
        if (event.getSource() == sprint) {
            message = addEntry("sprint");
        }
        if (event.getSource() == swim) {
            message = addEntry("swim");
        }
        if (event.getSource() == remove) {
            message = removeEntry();
        }
        outputArea.setText(message);
        //blankDisplay();
    } // actionPerformed

    public String findAllByDate() {
        try {
            int m = Integer.parseInt(month.getText());
            int d = Integer.parseInt(day.getText());
            int y = Integer.parseInt(year.getText());
            outputArea.setText("looking up record ...");
            String message = myAthletes.lookupEntries(d, m, y);
            return message;
        }
        catch(NumberFormatException e){
            return "Date fields must contain an integer";
        }
    }

    public String addEntry(String what) {
        try {
            String message = "Record added\n";
            System.out.println("Adding " + what + " entry to the records");
            String n = name.getText();
            int m = Integer.parseInt(month.getText());
            int d = Integer.parseInt(day.getText());
            int y = Integer.parseInt(year.getText());
            float km = java.lang.Float.parseFloat(dist.getText());
            int h = Integer.parseInt(hours.getText());
            int mm = Integer.parseInt(mins.getText());
            int s = Integer.parseInt(secs.getText());
            Entry e = new Entry(n, d, m, y, h, mm, s, km);
            if (what == "cycle") {
                String t = ter.getText();
                String tem = temp.getText();
                e = new CycleEntry(n, d, m, y, h, mm, s, km, t, tem);
            }
            if (what == "sprint") {
                int rep = Integer.parseInt(reps.getText());
                int re = Integer.parseInt(rec.getText());
                e = new SprintEntry(n, d, m, y, h, mm, s, km, rep, re);
            }
            if (what == "swim") {
                String wh = where.getText();
                e = new SwimEntry(n, d, m, y, h, mm, s, km, wh);
            }
            myAthletes.addEntry(e);
            return message;
        }
        catch(NumberFormatException e){
            return "All the fields except 'Name' must contain an integer";
        }
    }

    public String lookupEntry() {
        try {
            int m = Integer.parseInt(month.getText());
            int d = Integer.parseInt(day.getText());
            int y = Integer.parseInt(year.getText());
            outputArea.setText("looking up record ...");
            String message = myAthletes.lookupEntry(d, m, y);
            return message;
        }
        catch(NumberFormatException e){
            return "Date fields must contain an integer";
        }
    }

    public void blankDisplay() {
        name.setText("");
        day.setText("");
        month.setText("");
        year.setText("");
        hours.setText("");
        mins.setText("");
        secs.setText("");
        dist.setText("");

    }// blankDisplay
    // Fills the input fields on the display for testing purposes only
    public void fillDisplay(Entry ent) {
        name.setText(ent.getName());
        day.setText(String.valueOf(ent.getDay()));
        month.setText(String.valueOf(ent.getMonth()));
        year.setText(String.valueOf(ent.getYear()));
        hours.setText(String.valueOf(ent.getHour()));
        mins.setText(String.valueOf(ent.getMin()));
        secs.setText(String.valueOf(ent.getSec()));
        dist.setText(String.valueOf(ent.getDistance()));
    }
    public String removeEntry() {
        try {
            String n = name.getText();
            int m = Integer.parseInt(month.getText());
            int d = Integer.parseInt(day.getText());
            int y = Integer.parseInt(year.getText());
            return myAthletes.removeEntry(n, d, m, y);
        }
        catch(NumberFormatException e){
            return "Date fields must contain an integer";
        }
    }

} // TrainingRecordGUI

