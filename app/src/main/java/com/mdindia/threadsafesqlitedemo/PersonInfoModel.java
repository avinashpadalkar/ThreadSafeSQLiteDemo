package com.mdindia.threadsafesqlitedemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Faheem.Raza on 14/06/2017.
 */

public class PersonInfoModel implements Parcelable {
    private String fullName, email, age, mobile_no;

    public PersonInfoModel() {
    }

    public PersonInfoModel(String fullName, String email, String age, String mobile_no) {
        this.fullName = fullName;
        this.email = email;
        this.age = age;
        this.mobile_no = mobile_no;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    protected PersonInfoModel(Parcel in) {
        fullName = in.readString();
        email = in.readString();
        age = in.readString();
        mobile_no = in.readString();
    }

    public static final Creator<PersonInfoModel> CREATOR = new Creator<PersonInfoModel>() {
        @Override
        public PersonInfoModel createFromParcel(Parcel in) {
            return new PersonInfoModel(in);
        }

        @Override
        public PersonInfoModel[] newArray(int size) {
            return new PersonInfoModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(fullName);
        parcel.writeString(email);
        parcel.writeString(age);
        parcel.writeString(mobile_no);
    }
}
