package com.demo.softdreams.shared.common;



import java.util.HashMap;

public class ResponseConstance {

    private ResponseConstance() {}

    // response code
    // auth (start with prefix number = 9)
    public static final int AUTH_FORBIDDEN = 9999;
    public static final int AUTH_UNAUTHORIZED = 9998;
    public static final int AUTH_INVALID_REFRESH_TOKEN = 9997;
    public static final int AUTH_UNSUPPORTED_REFRESH_TOKEN = 9996;
    public static final int USERNAME_NOT_FOUND = 9995;
    public static final int OTP_CODE_IS_EXPIRED = 9994;
    public static final int OTP_CODE_IS_NOT_CORRECT = 9993;
    public static final int OVER_COUNT_OTP_CODE = 9992;
    public static final int SAVE_SECCESS = 9999;
    public static final int USERNAME_NOT_FOUND_OR_OFF_TWO_FACTOR = 9991;
    public static final int NOT_CORRECT_WITH_CURRENT_PWD = 9990;
    public static final int NEW_PWD_IS_SAME_CURRENT_PWD = 9989;
    public static final int NEW_PWD_IS_NOT_SAME_CONFIRM_NEW_PWD = 9988;
    public static final int TERMS_IS_NOT_ACCEPT = 9987;
    public static final int PHONE_IS_EXISTS = 9986;
    public static final int REFRESH_OTP_FAIL = 9985;
    public static final int USER_LOCKED = 9984;
    public static final int KEY_FORGOT_PASSWORD_NOT_FOUND = 9983;
    public static final int TIME_OF_URL_FORGOT_PASSWORD_INVALID = 9982;
//    public static final int UPDATE_TWO_FACTOR_FAIL = 9981;

    public static final int UPDATE_CATEGORY_SECCESS = 9981;
    public static final int UPDATE_BLOG_SECCESS = 9981;
    public static final int USER_HAS_LOGGED_CANNOT_DELETE = 9980;
    public static final int USER_IS_ACTIVE_CANNOT_DELETE = 9979;


    // validate (start with prefix number = 8)
    // validate login
    public static final int INVALID_LOGIN = 8999;
    // validate user upload game
    public static final int BUSINESS_CODE_DUPLICATED = 8998;
    public static final int PROVIDER_CODE_DUPLICATED = 8997;
    public static final int PHONE_NUMBER_DUPLICATED = 8996;
    public static final int EMAIL_DUPLICATED = 8995;
    public static final int USERNAME_DUPLICATED = 8994;
    public static final int PWD_AND_CONFIRM_PWD_NOT_MATCHED = 8993;
    public static final int USERNAME_HAS_CHANGE = 8992;
    public static final int STATUS_OF_USER_UPLOAD_GAME_NOT_CHANGE = 8991;
    public static final int STATUS_OF_USER_UPLOAD_IS_NOT_VALID = 8990;
    public static final int EMAIL_STAFF_DUPLICATED = 8989;
    public static final int PHONE_NUMBER_STAFF_DUPLICATED = 8988;
    public static final int USERNAME_STAFF_DUPLICATED = 8987;
    public static final int REPRESENT_PHONE_NUMBER_DUPLICATED = 8984;
    public static final int REPRESENT_EMAIL_DUPLICATED = 8983;

    // validate common field
    public static final int FIELD_INVALID = 8979;
    public static final int VIEW_ID_PROMOTION = 8977;
    public static final int DISPLAYNAME_DUPLICATED = 8976;
    public static final int TWO_FACTOR_ENABLED_REQUIRED_EMAIL = 8975;

    // error common (start with prefix number = 7)
    public static final int INTERNAL = 7999;
    public static final int EXCEPTION = 7998;
    public static final int USER_NOT_FOUND = 7997;
    public static final int USER_NOT_FOUND_BY_PHONE_NUMBER = 7996;
    public static final int USER_NOT_FOUND_BY_EMAIL = 7995;
    public static final int USER_NOT_SUPPORT_FORGOT_PASSWORD = 7994;

    public static final int BAD_QUERY = 7985;

    // success (success with prefix number = 6)
    public static final int LOGIN_SUCCESSFUL = 6999;
    public static final int REFRESH_TOKEN_SUCCESSFUL = 6998;
    public static final int LOGIN_TWO_FACTOR = 6997;
    public static final int REFRESH_OTP_SUCCESSFUL = 6996;
    public static final int CHANGE_PWD_SUCCESSFUL = 6995;
    public static final int OTP_CODE_IS_CORRECT = 6994;


    public static final int FIND_CATEGORY_SUCCESSFUL = 6966;
    public static final int FIND_BLOG_SUCCESSFUL = 6966;
    public static final int FIND_ALL_ACTION_SUCCESSFUL = 6965;

    public static final int DELETED_ACTION_SUCCESSFUL = 6965;


    public static final int NOT_FOUND_CATEGORY_BY_ID = 6932;

    public static final int NOT_FOUND_BLOG_BY_ID = 69300;





    // response message
    // auth
    public static final String AUTH_FORBIDDEN_MSG = "Access denied";
    public static final String AUTH_UNAUTHORIZED_MSG = "Bad credentials";
    public static final String AUTH_INVALID_REFRESH_TOKEN_MSG = "Invalid JWT refresh token";
    public static final String AUTH_UNSUPPORTED_REFRESH_TOKEN_MSG = "JWT refresh token is unsupported";
    public static final String USERNAME_OR_OTP_NOT_FOUND_MSG = "Username or OTP code is not expected";
    public static final String OTP_CODE_IS_EXPIRED_MSG = "OTP code is expired";
    public static final String OTP_CODE_IS_NOT_CORRECT_OR_OVER_COUNT_MSG = "OTP code is not correct";
    public static final String OVER_COUNT_OTP_CODE_MSG = "Number of enter OTP code exceeded.";
    public static final String USERNAME_NOT_FOUND_OR_OFF_TWO_FACTOR_MSG = "Username is not correct or disable 2-factor mode";
    public static final String REFRESH_OTP_SUCCESSFUL_MSG = "Refresh OTP code successful";
    public static final String CHANGE_PWD_SUCCESSFUL_MSG = "Change password successful";
    public static final String NOT_CORRECT_WITH_CURRENT_PWD_MSG = "Current password is not correct";
    public static final String NEW_PWD_IS_SAME_CURRENT_PWD_MSG = "The new password is the same as the current password";
    public static final String NEW_PWD_IS_NOT_SAME_CONFIRM_NEW_PWD_MSG = "The new password is not the same with the confirm new password";
    public static final String TERMS_IS_NOT_ACCEPT_MSG = "Terms is not accept";
    public static final String PHONE_IS_EXISTS_MSG = "Phone is exists";
    public static final String OTP_CODE_IS_CORRECT_MSG = "OTP code is correct";
    public static final String UPDATE_TWO_FACTOR_SUCCESSFUL_MSG = "Update 2-factor authentication of user successful";

    // common
    public static final String INTERNAL_SERVER_MSG = "The server has a problem, please try again";


    // successful
    public static final String LOGIN_SUCCESSFUL_MSG = "Login successful";
    public static final String REFRESH_TOKEN_SUCCESSFUL_MSG = "Refresh token successful";
    public static final String LOGIN_TWO_FACTOR_MSG = "Sent OTP to client";

    public static final String SAVE_SUBSCRIPTION_SUCCESSFUL_MSG = "Save subscription successful";
    public static final String SAVE_BLOG_SUCCESSFUL_MSG = "Save blog successful";
    public static final String SAVE_CATEGORY_SUCCESSFUL_MSG = "Save category successful";

    public static final String NOT_FOUND_CATEGORY_BY_ID_MSG = "Not found By CATEGORY by ID";
    public static final String NOT_FOUND_BLOG_BY_ID_MSG = "Not found By Blog by ID";

    // response hashmap
    public static final HashMap<Integer, String> MSG = new HashMap<>() {{
        put(AUTH_FORBIDDEN, AUTH_FORBIDDEN_MSG);
        put(AUTH_UNAUTHORIZED, AUTH_UNAUTHORIZED_MSG);
        put(INTERNAL, INTERNAL_SERVER_MSG);
        put(LOGIN_SUCCESSFUL, LOGIN_SUCCESSFUL_MSG);
        put(REFRESH_TOKEN_SUCCESSFUL, REFRESH_TOKEN_SUCCESSFUL_MSG);
        put(AUTH_INVALID_REFRESH_TOKEN, AUTH_INVALID_REFRESH_TOKEN_MSG);
        put(AUTH_UNSUPPORTED_REFRESH_TOKEN, AUTH_UNSUPPORTED_REFRESH_TOKEN_MSG);
        put(LOGIN_TWO_FACTOR, LOGIN_TWO_FACTOR_MSG);
        put(USERNAME_NOT_FOUND, USERNAME_OR_OTP_NOT_FOUND_MSG);
        put(OTP_CODE_IS_EXPIRED, OTP_CODE_IS_EXPIRED_MSG);
        put(OTP_CODE_IS_NOT_CORRECT, OTP_CODE_IS_NOT_CORRECT_OR_OVER_COUNT_MSG);
        put(OVER_COUNT_OTP_CODE, OVER_COUNT_OTP_CODE_MSG);
        put(USERNAME_NOT_FOUND_OR_OFF_TWO_FACTOR, USERNAME_NOT_FOUND_OR_OFF_TWO_FACTOR_MSG);
        put(REFRESH_OTP_SUCCESSFUL, REFRESH_OTP_SUCCESSFUL_MSG);
        put(CHANGE_PWD_SUCCESSFUL, CHANGE_PWD_SUCCESSFUL_MSG);
        put(NOT_CORRECT_WITH_CURRENT_PWD, NOT_CORRECT_WITH_CURRENT_PWD_MSG);
        put(NEW_PWD_IS_SAME_CURRENT_PWD, NEW_PWD_IS_SAME_CURRENT_PWD_MSG);
        put(NEW_PWD_IS_NOT_SAME_CONFIRM_NEW_PWD, NEW_PWD_IS_NOT_SAME_CONFIRM_NEW_PWD_MSG);
        put(PHONE_IS_EXISTS, PHONE_IS_EXISTS_MSG);
        put(TERMS_IS_NOT_ACCEPT, TERMS_IS_NOT_ACCEPT_MSG);
        put(OTP_CODE_IS_CORRECT, OTP_CODE_IS_CORRECT_MSG);
    }};
}
