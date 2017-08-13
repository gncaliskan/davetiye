package com.example.gncal.davetiye.Business;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gncal.davetiye.Model.Invitee;
import com.example.gncal.davetiye.Model.InviteeStatus;
import com.example.gncal.davetiye.R;

import java.util.List;

/**
 * Created by gncal on 8/2/2017.
 * Seçilen kişilerin artık davetli listesine atılması için adapter.
 * Kişi bilgilerine ek olarak gönderilme ve cevaplanma tarihleri, davetlilerin
 * cevapları gibi değişkenleri de tutar.
 * Kişi adları listede görünürken adın üstüne basınca açılan alanda detay
 * bilgileri görüntülenir.
 */

public class InviteeListAdapter extends BaseExpandableListAdapter {
    public List<Invitee> list_invitee;
    public Context context;

    public InviteeListAdapter(List<Invitee> list_invitee, Context context) {
        this.list_invitee = list_invitee;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return list_invitee.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Invitee getGroup(int groupPosition) {
        return list_invitee.get(groupPosition);
    }

    @Override
    public InviteeStatus getChild(int groupPosition, int childPosition) {
        return list_invitee.get(groupPosition).getStatus();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Invitee listTitle = (Invitee) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.invitee_item, null);
        }
        TextView inviteeName = (TextView) convertView
                .findViewById(R.id.tv_inviteeName);
        inviteeName.setTypeface(null, Typeface.BOLD);
        inviteeName.setText(listTitle.getName());

        ImageView inviteeColor = (ImageView) convertView.findViewById(R.id.iv_inviteeStatus);
        if(listTitle.getStatus().getAnswer()==1){
            inviteeColor.setBackgroundColor(Color.rgb(31, 120, 180));
        }else if(listTitle.getStatus().getAnswer()==-1){
            inviteeColor.setBackgroundColor(Color.rgb(186, 22, 63));
        }else{
            inviteeColor.setBackgroundColor(Color.rgb(153, 148, 194));
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final Invitee invitee =(Invitee) getGroup(groupPosition);
        final InviteeStatus invitee_status = (InviteeStatus) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.invitee_child_item, null);
        }
        TextView status = (TextView) convertView
                .findViewById(R.id.tv_inviteeChild_status);
        status.setText("Durum");

        TextView sendDate = (TextView) convertView
                .findViewById(R.id.tv_inviteeChild_sendDate);
        if(invitee_status.getSendDate()!=null) {
            sendDate.setText(invitee_status.getSendDate().toString());
        }else{
            sendDate.setText("--");
        }

        TextView answerDate = (TextView) convertView
                .findViewById(R.id.tv_inviteeChild_answerDate);
        if(invitee_status.getAnswerDate()!=null) {
            answerDate.setText(invitee_status.getAnswerDate().toString());
        }else{
            answerDate.setText("--");
        }

        TextView answer = (TextView) convertView
                .findViewById(R.id.tv_inviteeChild_answer);
        if(invitee.getStatus().getAnswer()==1){
           answer.setText("Katılıyor");
        }else  if(invitee.getStatus().getAnswer()==-1){
            answer.setText("Katılmıyor");
        }else{
            answer.setText("Belki");
        }

        TextView phoneNumber = (TextView) convertView
                .findViewById(R.id.tv_inviteeChild_phoneNumber);
        phoneNumber.setText(invitee.getPhoneNumber());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
