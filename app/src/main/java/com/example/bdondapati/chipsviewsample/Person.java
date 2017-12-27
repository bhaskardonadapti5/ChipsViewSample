package com.example.bdondapati.chipsviewsample;

import android.os.Parcel;
import android.os.Parcelable;


class Person implements Parcelable {
    private String name;
    private String email;

    Person(String n, String e) {
        name = n;
        email = e;
    }

    public String getName() { return name; }
    String getEmail() { return email; }

    @Override
    public String toString() { return name; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.email);
    }

    private Person(Parcel in) {
        this.name = in.readString();
        this.email = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}