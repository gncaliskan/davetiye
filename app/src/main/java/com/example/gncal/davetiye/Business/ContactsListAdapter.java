package com.example.gncal.davetiye.Business;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.gncal.davetiye.Model.Contact;
import com.example.gncal.davetiye.Model.ContactsList;
import com.example.gncal.davetiye.R;

import java.util.ArrayList;

/**
 * Created by gncal on 7/30/2017.
 * Rehberden çektiğimiz kişileri kendi listemize atabilmek için adapter.
 * Kullanıcı, kişilerin adını, telefon numarasını, telefon numarası türünü
 * görüp seçeceği kişilerin checkboxını işaretleyebilir.
 *
 * liste için layout....activity_contacts_picker
 * liste elemanları için layout.....contact_item
 */

public class ContactsListAdapter extends BaseAdapter {

    Context context;
    public ContactsList contactsList,filteredContactsList,selectedContactsList;
    String filterContactName;

    public ContactsListAdapter(Context context, ContactsList contactsList){

        super();
        this.context = context;
        this.contactsList = contactsList;
        this.filteredContactsList=new ContactsList();
        this.selectedContactsList = new ContactsList();
        this.filterContactName = "";
    }

    public void filter(String filterContactName){



        filteredContactsList.contactArrayList.clear();

        if(filterContactName.isEmpty() || filterContactName.length()<1){
            filteredContactsList.contactArrayList.addAll(contactsList.contactArrayList);
            this.filterContactName = "";

        }
        else {
            this.filterContactName = filterContactName.toLowerCase().trim();
            for (int i = 0; i < contactsList.contactArrayList.size(); i++) {

                if (contactsList.contactArrayList.get(i).getName().toLowerCase().contains(filterContactName))
                    filteredContactsList.addContact(contactsList.contactArrayList.get(i));
            }
        }
        notifyDataSetChanged();

    }

    public void addContacts(ArrayList<Contact> contacts){
        this.contactsList.contactArrayList.addAll(contacts);
        this.filter(this.filterContactName);

    }

    @Override
    public int getCount() {
        return filteredContactsList.getCount();
    }

    @Override
    public Contact getItem(int position) {
        return filteredContactsList.contactArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(this.getItem(position).getId());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if(convertView==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();

            convertView = inflater.inflate(R.layout.contact_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.chkContact = (CheckBox) convertView.findViewById(R.id.chk_contact);
            viewHolder.contactName = (TextView) convertView.findViewById(R.id.tv_contactName);
            viewHolder.contactNumber = (TextView) convertView.findViewById(R.id.tv_contactNumber);

            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.contactName.setText(this.filteredContactsList.contactArrayList.get(position).getName());
        viewHolder.contactNumber.setText(this.filteredContactsList.contactArrayList.get(position).getLabel()+" : "+this.filteredContactsList.contactArrayList.get(position).getPhoneNumber());
        viewHolder.chkContact.setId(Integer.parseInt(this.filteredContactsList.contactArrayList.get(position).getId()));
        viewHolder.chkContact.setChecked(alreadySelected(filteredContactsList.contactArrayList.get(position)));

        viewHolder.chkContact.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Contact contact = filteredContactsList.getContact(buttonView.getId());

                if(contact!=null && isChecked && !alreadySelected(contact)){
                    selectedContactsList.addContact(contact);
                }
                else if(contact!=null && !isChecked){
                    selectedContactsList.removeContact(contact);
                }
            }
        });

        return convertView;
    }

    public boolean alreadySelected(Contact contact)
    {
        if(this.selectedContactsList.getContact(Integer.parseInt(contact.getId()))!=null)
            return true;

        return false;
    }

    public static class ViewHolder{

        CheckBox chkContact;
        TextView contactName;
        TextView contactNumber;
    }
}
