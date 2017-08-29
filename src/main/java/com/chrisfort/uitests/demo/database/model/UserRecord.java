package com.chrisfort.uitests.demo.database.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Set;


/**
 * Created by cpfort on 8/29/17.
 */
@Document(collection = "UserRecords")
public class UserRecord {

    @Id
    private String guid;

    private String username;

    private String firstName;

    private String lastName;

    private String nickName;

    private String password;

    private Set<PhoneNumber> phoneNumbers;

    private Set<EmailAddress> emailAddresses;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public Set<EmailAddress> getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(Set<EmailAddress> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public class EmailAddress {
        private boolean confirmed;

        private String emailAddress;

        private HashMap<String, String> attributes;

        public EmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }

        public boolean isConfirmed() {
            return confirmed;
        }

        public void setConfirmed(boolean confirmed) {
            this.confirmed = confirmed;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }

        public HashMap<String, String> getAttributes() {
            return attributes;
        }

        public void setAttributes(HashMap<String, String> attributes) {
            this.attributes = attributes;
        }
    }


    public class PhoneNumber {
        private String countryCode;

        private String phoneNumber;

        private boolean confirmed;

        private String country;

        private String name;

        private HashMap<String, String> attributes;

        public PhoneNumber(String countryCode, String phoneNumber, String country) {
            this.countryCode = countryCode;
            this.phoneNumber = phoneNumber;
            this.country = country;
        }

        public boolean isConfirmed() {
            return confirmed;
        }

        public void setConfirmed(boolean confirmed) {
            this.confirmed = confirmed;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public HashMap<String, String> getAttributes() {
            return attributes;
        }

        public void setAttributes(HashMap<String, String> attributes) {
            this.attributes = attributes;
        }

        public String getCountryCode() {

            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }


}
