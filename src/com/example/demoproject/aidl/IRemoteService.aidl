// IRemoteService.aidl
package com.example.demoproject.aidl;

import com.example.demoproject.service_started_bound.Person;

// Declare any non-default types here with import statements

/** Example service interface */
interface IRemoteService {
    /** Request the process ID of this service, to do evil things with it. */
    int getPid();

    /** Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    List<Person> getPerson();

    void addPerson(in Person person);
}