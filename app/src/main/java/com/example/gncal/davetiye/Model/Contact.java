package com.example.gncal.davetiye.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gncal on 7/27/2017.
 */

/* Sayfalar arası taşınacak nesneler için serializable ya da parcelable sınıfları
kullanılır. Parcelable sınıfı serializable sınıfına göre çok daha hızlıdır.
Rehberden okunup davetli listesine atılacak nesneler için parcelable sınıfından
yararlanıyoruz.
 */
public class Contact implements Parcelable {
    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    //name: kişi adı, phoneNumber: kayıtlı telefon numarası, label. o telefon numarasının türü (mobile, home, work vb.)
    private String id, name, phoneNumber, label;

    public Contact(String id, String name, String phoneNumber, String label) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.label = label;
    }

    protected Contact(Parcel in) {
        id = in.readString();
        name = in.readString();
        phoneNumber = in.readString();
        label = in.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(phoneNumber);
        dest.writeString(label);
    }
}
