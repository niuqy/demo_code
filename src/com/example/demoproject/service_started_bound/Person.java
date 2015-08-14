package com.example.demoproject.service_started_bound;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by abner on 8/13/15.
 */
public class Person implements Parcelable {
    public int age;
    public String name;

    public Person(){

    }

    private Person(Parcel in){
        readFromParcel(in);
    }

    public static Creator<Person> CREATOR = new Creator<Person>(){

        @Override
        public Person createFromParcel(Parcel parcel) {
            return new Person(parcel);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeInt(age);
        out.writeString(name);
    }

    public void readFromParcel(Parcel in){
        age = in.readInt();
        name = in.readString();
    }
}
