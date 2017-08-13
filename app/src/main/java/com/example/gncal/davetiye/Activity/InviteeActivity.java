package com.example.gncal.davetiye.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.gncal.davetiye.Business.InviteeListAdapter;
import com.example.gncal.davetiye.Model.Contact;
import com.example.gncal.davetiye.Model.Invitee;
import com.example.gncal.davetiye.Model.InviteeStatus;
import com.example.gncal.davetiye.R;

import java.util.ArrayList;

public class InviteeActivity extends AppCompatActivity {

    ArrayList<Contact> contacts = new ArrayList<Contact>();
    ArrayList<Invitee> invitees = new ArrayList<>();
    ExpandableListView invitee_expandable;
    InviteeListAdapter invitee_adapter;

    Button pickContacts;
    final int CONTACT_PICK_REQUEST = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitee);


        pickContacts = (Button) findViewById(R.id.btn_pick_contacts);

        pickContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentContactPick = new Intent(InviteeActivity.this,ContactsPickerActivity.class);
                InviteeActivity.this.startActivityForResult(intentContactPick,CONTACT_PICK_REQUEST);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CONTACT_PICK_REQUEST && resultCode == RESULT_OK){
            contacts = data.getParcelableArrayListExtra("SelectedContacts");

            convertContactsToInvitees();
            invitee_adapter = new InviteeListAdapter(invitees,getApplicationContext());

            invitee_expandable = (ExpandableListView) findViewById(R.id.lv_invitee_expandable);
            invitee_expandable.setAdapter(invitee_adapter);
            invitee_expandable.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                @Override
                public void onGroupExpand(int groupPosition) {
                    Toast.makeText(getApplicationContext(),
                            ((Invitee)invitee_adapter.getGroup(groupPosition)).getName()+ " List Expanded.",
                            Toast.LENGTH_SHORT).show();
                }
            });

            invitee_expandable.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

                @Override
                public void onGroupCollapse(int groupPosition) {
                    Toast.makeText(getApplicationContext(),
                            ((Invitee)invitee_adapter.getGroup(groupPosition)).getName() + " List Collapsed.",
                            Toast.LENGTH_SHORT).show();

                }
            });

            invitee_expandable.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v,
                                            int groupPosition, int childPosition, long id) {
                    Toast.makeText(
                            getApplicationContext(),
                            ((Invitee)invitee_adapter.getGroup(groupPosition)).getName()
                                    + " -> "
                                    + ((InviteeStatus)invitee_adapter.getChild(groupPosition,childPosition)).isAnswered(), Toast.LENGTH_SHORT
                    ).show();
                    return false;
                }
            });
        }

    }

    private void convertContactsToInvitees() {
        for (Contact c: contacts) {
            InviteeStatus is= new InviteeStatus(false,false,false,null,null,0);
            Invitee in= new Invitee(c.getName(), c.getPhoneNumber(),is);
            invitees.add(in);
        }

    }




}
