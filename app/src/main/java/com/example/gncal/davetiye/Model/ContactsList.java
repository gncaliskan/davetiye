package com.example.gncal.davetiye.Model;

import java.util.ArrayList;

/**
 * Created by gncal on 7/31/2017.
 */

public class ContactsList {

    public ArrayList<Contact> contactArrayList;

    public ContactsList(){

        contactArrayList = new ArrayList<Contact>();
    }



    public int getCount(){

        return contactArrayList.size();
    }

    public void addContact(Contact contact){
        contactArrayList.add(contact);
    }

    public  void removeContact(Contact contact){
        contactArrayList.remove(contact);
    }

    public Contact getContact(int id){

        for(int i=0;i<this.getCount();i++){
            if(Integer.parseInt(contactArrayList.get(i).getId())==id)
                return contactArrayList.get(i);
        }

        return null;
    }




}
