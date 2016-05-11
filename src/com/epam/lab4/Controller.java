package com.epam.lab4;

import com.epam.lab4.com.epam.lab4.model.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

import static com.epam.lab4.View.EMPTY;

public class Controller {

    /* Patterns for different fields */
    private static final String NAME_PATTERN = "^[A-Za-z\\-']{2,30}$"; // Maria
    private static final String LOGIN_PATTERN = "^[a-z0-9]{5,20}$"; // maria82
    private static final String DATE_PATTERN = "^[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}$"; // 25.33.1042
    private static final String EMAIL_PATTERN =
            "^[A-Za-z0-9\\.\\-\\_]+@[a-zA-Z0-9\\_\\.\\-]+\\.[A-Za-z]{2,}$"; // 1my.ex-am_ple@info.wow.com
    private static final String GENDER_PATTERN = "^[mf]$"; // m or f
    private static final String STATUS_PATTERN = "^[1-7]$"; // 5
    private static final String PHONE_PATTERN = "^\\+?[0-9]{3}-?[0-9]{6,12}$"; // +077-1234567, +0771234567, 0771234567
    private static final String GEO_PATTERN = "^([A-Za-z\\-']+\\s?)+$"; // National Aviation University, USA, Ukraine
    private static final String WORK_PATTERN = "^\\S.*$"; // Google Inc.

    /* Limits of birth date and its format */
    private static final String MIN_DATE = "01.01.1900";
    private static final String MAX_DATE = "01.01.2010";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    /** Delimiter for list output */
    private static final String DELIMITER = "\n";

    Model model;
    View view;

    /* Constructor with parameters */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    // Work method
    public void processUser() {
        Scanner scanner = new Scanner(System.in);

        view.printMessage(View.INPUT_INFO);

        model.setName(inputWithScanner(scanner, View.NAME_INPUT, NAME_PATTERN, false));
        model.setSurname(inputWithScanner(scanner, View.SURNAME_INPUT, NAME_PATTERN, false));
        model.setLogin(inputWithScanner(scanner, View.LOGIN_INPUT, LOGIN_PATTERN, false));
        model.setBirthDate(inputDateWithScanner(scanner, View.BIRTH_DATE_INPUT, DATE_PATTERN));
        model.setGender(inputWithScanner(scanner, View.GENDER_INPUT, GENDER_PATTERN, false));
        model.setFamilyStatus(Integer.valueOf
                (inputWithScanner(scanner, View.FAMILY_STATUS +
                                    view.listToString(View.FAMILY_STATUS_INPUT, DELIMITER),
                                    STATUS_PATTERN, false)) - 1);
        model.setEmail(inputWithScanner(scanner, View.EMAIL_INPUT, EMAIL_PATTERN, false));
        model.setCountry(inputWithScanner(scanner, View.COUNTRY_INPUT, GEO_PATTERN, false));

        // optional fields
        model.setSkype(inputWithScanner(scanner, View.SKYPE_INPUT + View.OPTIONAL, LOGIN_PATTERN, true));
        model.setUniversity(inputWithScanner(scanner, View.UNIVERSITY_INPUT + View.OPTIONAL, GEO_PATTERN, true));
        model.setWorkplace(inputWithScanner(scanner, View.WORK_INPUT + View.OPTIONAL, WORK_PATTERN, true));
        model.setPhoneNumber(inputPhoneWithScanner(scanner, View.PHONE_INPUT + View.OPTIONAL, PHONE_PATTERN, true));
        model.setRegistrationDate(getCurrentDate(DATE_FORMAT));
        model.setAbout(inputWithScanner(scanner, View.ABOUT_INPUT + View.OPTIONAL, WORK_PATTERN, true));

        view.printMessage(View.RESULT);
        view.printMessage(view.toString(model, DELIMITER));
    }

    // The Utility methods

    /**
     * Reads and validates user's input, depending on the given pattern.
     *
     * @param scanner Scanner to read from.
     * @param message Message to print to user.
     * @param pattern Pattern to validate input with.
     * @param optional Indicator if the field is optional. If it is, accepts empty string as valid input.
     * @return Validated user's input.
     */
    private String inputWithScanner(Scanner scanner, String message, String pattern, boolean optional) {
        String str;
        view.printMessage(message);

        while (true) {
            str = scanner.nextLine();

            if (optional && str.isEmpty()) {
                str = EMPTY;

                break;
            } else if (!checkInput(str, pattern)) {
                view.printMessage(View.WRONG_INPUT);
            } else { // Input is correct
                break;
            }
        }

        return str;
    }

    /**
     * Reads and validates user's input date, checking format and range of the date.
     *
     * @param scanner Scanner to read from.
     * @param message Message to print to user.
     * @param pattern Pattern to validate input with.
     * @return Validated user's input date.
     */
    private String inputDateWithScanner(Scanner scanner, String message, String pattern) {
        String date;

        while (true) {
            date = inputWithScanner(scanner, message, pattern, false);

            if (dateIsWithinRange(date, MIN_DATE, MAX_DATE)) {
                break;
            } else { // Date is out of range
                view.printMessage(View.OUT_RANGE_INPUT);
            }
        }

        return date;
    }

    /**
     * Reads and validates user's input phone number, checking its format and returns it with only digits.
     *
     * @param scanner Scanner to read from.
     * @param message Message to print to user.
     * @param pattern Pattern to validate input with.
     * @param optional Indicator if the field is optional. If it is, accepts empty string as valid input.
     * @return Validated user's input phone number.
     */
    private String inputPhoneWithScanner(Scanner scanner, String message, String pattern, boolean optional) {
        String phoneNumber = inputWithScanner(scanner, message, pattern, optional);

        if (!phoneNumber.equals(View.EMPTY)) {
            return getDigits(phoneNumber);
        } else {
            return phoneNumber;
        }
    }

    /**
     * Checks if the input string matches given pattern.
     *
     * @param input   Input string to check.
     * @param pattern Pattern to check with.
     * @return boolean.
     */
    private boolean checkInput(String input, String pattern) {
        if (input.matches(pattern)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if date is within given range.
     *
     * @param dateString Date to check.
     * @param startDateString Min date.
     * @param endDateString Max date.
     * @return boolean.
     */
    private boolean dateIsWithinRange(String dateString, String startDateString, String endDateString) {
        try {
            Date date = DATE_FORMAT.parse(dateString);
            Date startDate = DATE_FORMAT.parse(startDateString);
            Date endDate = DATE_FORMAT.parse(endDateString);

            return !(date.before(startDate) || date.after(endDate));
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Returns string with current date, formatted by given format.
     *
     * @param simpleDateFormat Format of date.
     * @return Formatted current date.
     */
    private String getCurrentDate(SimpleDateFormat simpleDateFormat) {
        return simpleDateFormat.format(new Date()).toString();
    }

    /**
     * Returns string containing only digits from the input string.
     *
     * @param string Input string.
     * @return String with digits from the input string.
     */
    private String getDigits(String string) {
        return string.replaceAll("[\\D]", "");
    }
}