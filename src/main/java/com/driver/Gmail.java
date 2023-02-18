package com.driver;

import java.util.ArrayList;
import java.util.Date;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)

    private ArrayList<Date> inboxDate;
    private ArrayList<String> inboxSender;
    private ArrayList<String> inboxMessage;

    private ArrayList<Date> trashDate;
    private ArrayList<String> trashSender;
    private ArrayList<String> trashMessage;
    public Gmail(String emailId, int inboxCapacity1) {
        super(emailId);
        this.inboxCapacity = inboxCapacity1;

        this.inboxDate = new ArrayList<>();
        this.inboxSender = new ArrayList<>();
        this.inboxMessage = new ArrayList<>();
        this.trashDate = new ArrayList<>();
        this.trashSender = new ArrayList<>();
        this.trashMessage = new ArrayList<>();
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.

        if(inboxMessage.size() >= this.inboxCapacity){
            Date oldDate = inboxDate.get(0);
            String oldMessage = inboxMessage.get(0);
            String oldSender = inboxSender.get(0);

            inboxDate.remove(0);
            inboxMessage.remove(0);
            inboxSender.remove(0);
            System.out.println(oldSender+" "+oldMessage);
            trashDate.add(oldDate);
            trashMessage.add(oldMessage);
            trashSender.add(oldSender);
        }
        inboxDate.add(date);
        inboxSender.add(sender);
        inboxMessage.add(message);
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing

        int index = -1;
        for(int i = 0; i < inboxMessage.size(); i++){
            if(message.equals(inboxMessage.get(i))){
                Date deleteDate = inboxDate.get(i);
                String deleteSender = inboxSender.get(i);
                String deleteMessage = inboxMessage.get(i);

                inboxDate.remove(i);
                inboxMessage.remove(i);
                inboxSender.remove(i);

                trashDate.add(deleteDate);
                trashSender.add(deleteSender);
                trashMessage.add(deleteMessage);

                break;
            }
        }

    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox

        if(inboxMessage.size() == 0){
            return null;
        }
        return inboxMessage.get(inboxMessage.size()-1);

    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox

        if(inboxMessage.size() == 0){
            return null;
        }
        return inboxMessage.get(0);
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int mails = 0;
        for(int i = 0; i < inboxMessage.size(); i++){
            Date date = inboxDate.get(i);
            if(date.compareTo(start) >= 0 && date.compareTo(end) <= 0){
                mails++;
            }
        }
        return mails;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inboxMessage.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trashMessage.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trashDate.clear();
        trashMessage.clear();
        trashSender.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return this.inboxCapacity;
    }
}
