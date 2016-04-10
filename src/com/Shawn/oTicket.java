package com.Shawn;

import org.omg.CORBA.DATA_CONVERSION;

import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Wolfknightx on 4/9/2016.
 */
public class oTicket {
    private Date ticketDate = new Date(); //information will be added based on the current date.
    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(ticketDate); //format found on Stackoverflow for formatting String to something more compact and eaiser to read.
    private String reporterName;
    private String issueDesc;
    private String severity; //severity rating will go from a level 1 to 5. 1 being most important 5 being least.
    private int ticketID; //this will attribute a ticket number based on the order added.

    public oTicket(Date todaysDate, String reporter, String issue, String severity, int ticketID){
        this.severity = severity;
        this.ticketDate = ticketDate;
        this.reporterName = reporter;
        this.issueDesc = issue;
        this.ticketID = ticketID;
    }

    @Override
    public String toString(){
        return "Severity Rating: " + severity + ", ID: " + ticketID + ", Reported On: " + modifiedDate + ", By: "
                + reporterName + ", For Issue: " + issueDesc + ".";
    }
}
