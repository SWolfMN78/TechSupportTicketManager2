package com.Shawn;

import javafx.scene.input.DataFormat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by Wolfknightx on 4/9/2016.
 */
public class TicketGUI extends JFrame{
    private JPanel rootPanel;
    private JTextField txtReporterName;
    private JTextField txtIssueDescription;
    private JComboBox cmbxSeverity;
    private JButton clearIssueInfoButton;
    private JButton btnAddNewIssue;
    private JList<oTicket> lstOpenTickets;
    private JButton btnResolveTicket;
    private JList<oTicket> lstClosedTickets;
    private JButton exitButton;
    private JScrollBar scrollBar1;
    private JScrollBar scrollBar2;
    private int ticketCounter; //ticketcounter to count the number of tickets in the list box for tracking.
    Date todaysDate = new Date();

    DefaultListModel<oTicket> ticketDefaultListModel;
    DefaultListModel<oTicket> closedTicketsModel;

    protected TicketGUI() {
        super("Tech Support Tickets");
        setContentPane(rootPanel);
        setPreferredSize(new Dimension(1500, 850));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        ticketDefaultListModel = new DefaultListModel<>();
        lstOpenTickets.setModel(ticketDefaultListModel);
        lstOpenTickets.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        closedTicketsModel = new DefaultListModel<>();
        lstClosedTickets.setModel(closedTicketsModel);
        lstClosedTickets.setSelectedIndex(ListSelectionModel.SINGLE_SELECTION);

        //call method to start the rating info.
        configureSeverityInfo();

        //call methods to handle the buttons
        configureButtons();
    }

    private void configureSeverityInfo(){
        //for string which will handle building up the severity that will be used.  Range 1-5
        for (int x = 1; x < 6; x++){
            if (x < 6){
                cmbxSeverity.addItem("Level " + x);
            }
        }
    }

    private void configureButtons(){
        btnAddNewIssue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pull the information from the textboxes and apply them into the method to be used. The date will be autofilled.
                String userName;
                String issueReported;
                String severityRating = (String) cmbxSeverity.getSelectedItem();

                //Validate that the user entered information into the different fields.
                try{
                    userName = txtReporterName.getText();
                    issueReported = txtIssueDescription.getText();
                    if (userName.equals("")){ //if the user's information is blank then throw an error msg.
                        JOptionPane.showMessageDialog(TicketGUI.this, "You must enter a user name.");
                        return;
                    }

                    if (issueReported.equals("")){ //check to make sure information is entered.
                        JOptionPane.showMessageDialog(TicketGUI.this, "You must enter information for the issue.");
                        return;
                    }
                }catch (Exception se){
                    JOptionPane.showMessageDialog(TicketGUI.this, "Error!");
                    return;
                }

                //build the entered information into the listbox.
                oTicket newTicket = new oTicket(todaysDate, userName, issueReported, severityRating, ticketCounter);
                ticketDefaultListModel.addElement(newTicket);
                ticketCounter++; //This will count the ID up as new ones are added.
            }
        });
        clearIssueInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //clear the information in the issues info section.
                txtReporterName.setText(" ");
                txtIssueDescription.setText(" "); //make the information in this textbox an empty string.
                cmbxSeverity.setSelectedIndex(0); //set the combobox back to the 1st item.
                txtReporterName.requestFocus(); //use of Stackover flow once the information is cleared focus the text her.
            }
        });
        btnResolveTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //grab the selected item from the Open list and move it.
                oTicket toRemove = lstOpenTickets.getSelectedValue();
                ticketDefaultListModel.removeElement(toRemove);
                closedTicketsModel.addElement(toRemove);

            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
