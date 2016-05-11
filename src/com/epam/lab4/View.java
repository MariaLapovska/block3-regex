package com.epam.lab4;

import com.epam.lab4.com.epam.lab4.model.Model;

public class View {

    /** Constant to indicate empty fields */
    public static final String EMPTY = "(none)";

    public static final String INPUT_INFO = "Welcome! To create new profile, fill the fields below:";
    public static final String WRONG_INPUT = "Wrong input format! Try again: ";
    public static final String OUT_RANGE_INPUT = "Date is out of range! Try again: ";
    public static final String NAME_INPUT = "Name (from 2 to 30 letters): ";
    public static final String SURNAME_INPUT = "Surname (from 2 to 30 letters): ";
    public static final String LOGIN_INPUT = "Login (from 5 to 20 letters and/or digits): ";
    public static final String BIRTH_DATE_INPUT = "Birth date (dd.mm.yyyy): ";
    public static final String GENDER_INPUT = "Gender (m / f): ";
    public static final String FAMILY_STATUS = "Family status: ";
    public static final String EMAIL_INPUT = "Email (example@info.com): ";
    public static final String COUNTRY_INPUT = "Country: ";
    public static final String SKYPE_INPUT = "Skype: ";
    public static final String UNIVERSITY_INPUT = "University: ";
    public static final String WORK_INPUT = "Work place: ";
    public static final String PHONE_INPUT = "Phone number (+077-1234567): ";
    public static final String ABOUT_INPUT = "Additional information: ";
    public static final String REG_DATE = "Registration date: ";
    public static final String OPTIONAL = "(optional, press Enter to skip)";
    public static final String RESULT = "Your profile is completed. Here's the result:";
    public static final String[] FAMILY_STATUS_INPUT = {"1 - Single",
                                                        "2 - Dating",
                                                        "3 - Engaged",
                                                        "4 - Married",
                                                        "5 - Complicated",
                                                        "6 - Divorced",
                                                        "7 - Widowed" };

    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Transforms string array to single string, where array elements are separated with delimiter.
     *
     * @param messageList String array to format.
     * @param delimiter Delimiter to separate string array elements.
     * @return Formatted string with array elements.
     */
    public String listToString(String[] messageList, String delimiter) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");

        for (String message : messageList) {
            stringBuilder.append(message + delimiter);
        }

        return stringBuilder.toString();
    }

    /**
     * Transforms Model object to string, where fields are preceded by description and followed by delimiter.
     *
     * @param model Model object to format.
     * @param delimiter Delimiter to separate Model object fields.
     * @return Formatted Model object.
     */
    public String toString(Model model, String delimiter) {
        return  NAME_INPUT + model.getName() + delimiter +
                SURNAME_INPUT + model.getSurname() + delimiter +
                LOGIN_INPUT + model.getLogin() + delimiter +
                BIRTH_DATE_INPUT + model.getBirthDate() + delimiter +
                GENDER_INPUT + model.getGender() + delimiter +
                FAMILY_STATUS + model.getFamilyStatus().toString() + delimiter +
                EMAIL_INPUT + model.getEmail() + delimiter +
                COUNTRY_INPUT + model.getCountry() + delimiter +
                SKYPE_INPUT + model.getSkype() + delimiter +
                UNIVERSITY_INPUT + model.getUniversity() + delimiter +
                WORK_INPUT + model.getWorkplace() + delimiter +
                PHONE_INPUT + formatPhoneNumber(model.getPhoneNumber()) + delimiter +
                ABOUT_INPUT + model.getAbout() + delimiter +
                REG_DATE + model.getRegistrationDate() + "\n";
    }

    /**
     * Formats phone number as following: +xxx xxx-xxxxxx.
     *
     * @param phoneNumber Number to format.
     * @return Formatted phone number.
     */
    public String formatPhoneNumber(String phoneNumber) {
        if (!phoneNumber.equals(EMPTY)) {
            return "+" + phoneNumber.substring(0, 3) + " " +
                    phoneNumber.substring(3, 6) + "-" + phoneNumber.substring(6);
        } else {
            return phoneNumber;
        }
    }
}