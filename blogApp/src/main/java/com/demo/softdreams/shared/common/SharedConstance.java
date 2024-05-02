package com.demo.softdreams.shared.common;



import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SharedConstance {
    public static class GENDER {
        public GENDER() {
        }

        public static final String MALE = "MALE";
        public static final String FEMALE = "FEMALE";
        public static final String OTHER = "OTHER";

    }

    public static class AGE_TYPE {
        public AGE_TYPE() {
        }

        public static final String ADULT = "MALE";
        public static final String ALL = "FEMALE";

        public static final String CHILDREN = "CHILDREN";
    }

    public static class IMAGE_TYPE {
        public IMAGE_TYPE() {
        }

        public static final String UNAVAILABLE = "UNAVAILABLE";
    }


    public static class ActiveInd {

        private ActiveInd() {
        }

        public static final int ACTIVE = 1;
        public static final int INACTIVE = 0;
        public static final int OTP_NOT_VALID = -1;
    }

    public static class BLOG {
        public BLOG() {
        }

        public static final String PUBLIC = "PUBLIC";
        public static final String PRIVATE = "PRIVATE";

        public static final String COMMENT_FOR_BLOG = "COMMENT_FOR_BLOG";
    }



        public static class DeleteInd {

            private DeleteInd() {
            }

            public static final boolean DELETED = true;
            public static final boolean NOT_DELETE = false;
        }

        public static class HasRead {

            private HasRead() {
            }

            public static final boolean READ = true;
            public static final boolean UN_READ = false;
        }

        public enum AUTH_KEY {
            TOKEN, REFRESH_TOKEN
        }


        public enum TWO_FACTOR {
            SMS, EMAIL, OFF
        }

        public enum SEX {
            MALE, FEMALE
        }


        public static class PermissionsCode {

            private PermissionsCode() {
            }


            public static class Admin {
                // display Permisson
                private Admin() {
                }




                public static final String ADMIN_MANAGE_POPUP_VIEW = "ADMIN_MANAGE_POPUP_VIEW";
                public static final String ADMIN_MANAGE_POPUP_CREATE = "ADMIN_MANAGE_POPUP_CREATE";
                public static final String ADMIN_MANAGE_POPUP_UPDATE = "ADMIN_MANAGE_POPUP_UPDATE";
                public static final String ADMIN_MANAGE_POPUP_DELETE = "ADMIN_MANAGE_POPUP_DELETE";
            }


        }

        public enum AGE_TYPEE {
            CHILDREN, ADULT, ALL
        }




        public static class Email {

            private Email() {
            }

            public static final String EMAIL_DEFAULT_FROM = "no-reply@gportal.vn";
            public static final String EMAIL_LOGIN_WITH_TWO_FACTOR_SUBJECT = "Xác thực đăng nhập ";
            public static final String EMAIL_LOGIN_WITH_TWO_FACTOR_CONTENT = "<b>Xin chào,</b><br>" +
                    "Chúng tôi vừa nhận được yêu cầu đăng nhập từ tài khoản {0}. Vui lòng nhập mã xác thực để hoàn thành quá trình đăng nhập. " +
                    "Mã xác thực của bạn là: {1}<br>" +
                    "Nếu không phải bạn yêu cầu đăng nhập, vui lòng bỏ qua email này.";







        }

        public static class Regex {

            private Regex() {
            }

            public static final String REGEX_EMAIL = "^[a-zA-Z0-9._-]{1,64}@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9]+$";
            public static final String REGEX_PHONE = "(^\\s*(\\+[0-9]{2,19})\\s*$)|(^\\s*([0-9]{3,20})\\s*$)";
            public static final String REGEX_PASSWORD = "^[^áàảãạÁÀẢÃẠăắằẳẵặĂẮẰẲẴẶâấầẩẫậÂẤẦẨẪẬéèẻẽẹÉÈẺẼẸêếềểễệÊẾỀỂỄỆíìỉĩịÍÌỈĨỊýỳỷỹỵÝỲỶỸỴóòỏõọÓÒỎÕỌôồốổỗộÔỒỐỔỖỘơớờởỡợƠỚỜỞỠỢúùủũụÚÙỦŨỤưứừửữựƯỨỪỬỮỰ]{6,30}$";
            public static final String REGEX_NOT_BLANK = "^(?!\\s*$).+";
            public static final String REGEX_CODE = "^[^\\sáàảãạÁÀẢÃẠăắằẳẵặĂẮẰẲẴẶâấầẩẫậÂẤẦẨẪẬéèẻẽẹÉÈẺẼẸêếềểễệÊẾỀỂỄỆíìỉĩịÍÌỈĨỊýỳỷỹỵÝỲỶỸỴóòỏõọÓÒỎÕỌôồốổỗộÔỒỐỔỖỘơớờởỡợƠỚỜỞỠỢúùủũụÚÙỦŨỤưứừửữựƯỨỪỬỮỰ]+$";
            public static final String REGEX_ONLY_NUMBER = "^[0-9]+$";
            public static final String REGEX_VIET_NAM_PHONE_NUMBER = "^(0|84|\\+84|\\(\\+84\\))(2(0[3-9]|1[0-6|8|9]|2[0-2|5-9]|3[2-9]|4[0-9]|5[1|2|4-9]|6[0-3|9]|7[0-7]|8[0-9]|9[0-4|6|7|9])|3[2-9]|5[5|6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])([0-9]{7})$";
            public static final String REGEX_PHONE_NUMBER = "^[\\d]+$";
            public static final List<String> PREFIX_PHONE_NUMBER = List.of("0", "+84", "(+84)");
            public static final String DEFAULT_PREFIX_PHONE_NUMBER = "84";
            public static final String REGEX_USERNAME = "^[a-zA-Z0-9]{3,20}$";
        }

        public enum ACTION_TYPE {
            LOGIN, FG_PWD, TWO_FACTOR
        }

        public enum TARGET_TYPE {
            GAME
        }


        public enum TARGET_ACTION {
            LOGIN, LOGOUT, CREATE, UPDATE, DELETE, EXTEND_SUBSCRIPTION, SUB_SUBSCRIPTION, CANCEL_SUBSCRIPTION,
            OTHER_SUBSCRIPTION, UNKNOW_SUBSCRIPTION, PLAY_GAME,
            REVIEW_GAME, LIKE_GAME, COMMENT, REPORT, SEARCH_GAME, VIEW_GAME,
            ADD_GAME_TO_SUBSCRIPTION, REMOVE_GAME_FROM_SUBSCRIPTION,
            LANDING_PAGE, POPUP
        }


        public enum ACTION_INTERACT_COMMENT {
            LIKE_COMMENT, HIDDEN_COMMENT, DELETE_COMMENT
        }

        public enum TYPE_ACCOUNT {
            WEB_ADMIN, WEB_END_USER, WEB_GAME_PROVIDER
        }

        public static class TargetStatus {

            private TargetStatus() {
            }

            public static final int SUCCESS = 1;
            public static final int FAIL = 0;
        }

        public static class BlacklistInd {

            private BlacklistInd() {
            }

            public static final boolean ACTIVE = true;
            public static final boolean INACTIVE = false;
        }

        public enum TARGET_TYPE_USER_HISTORY {
            GAME, SUBSCRIPTION, LOGIN, LOGOUT, REVIEW, COMMENT, PLAY_GAME, USER
        }

        public enum TYPE_USER_NOTIFICATION {
            GAME, NOTIFICATION, PROMOTION
        }


        public enum ACTION_USER_NOTIFICATION {
            NEW_GAME, REISSUED_GAME, NEW_EVENT, ABOUT_TO_EXPIRE_SUBSCRIPTION, EXPIRE_SUBSCRIPTION, SUB_SUBSCRIPTION, REWARD_GAME, LIKE_GAME, REVIEW_GAME, COMMENT, REPORT, ACTIVE_GAME, SUBSCRIPTION_NOTIFY
        }


        public enum ACTION_TARGET_TYPE {
            SUBSCRIPTION, NOTIFICATION, PROMOTION, MINIGAME
        }

        public enum PART_NAME {
            BLACK_NUT, GOAMA
        }





        public static class KeyJwt {

            private KeyJwt() {
            }

            public static final String ID = "id";
            public static final String USERNAME = "username";
            public static final String PHONE_NUMBER = "phoneNumber";
            public static final String EMAIL = "email";
            public static final String TYPE = "type";
            public static final String AUTHORITIES = "authorities";
            public static final String FOR_AGE = "forAge";
        }




        public static class PromotionTarget {

            private PromotionTarget() {
            }

            public static final String PROMOTION = "PROMOTION";
            public static final String NOTIFICATION = "NOTIFICATION";
        }

        public static class PromotionType {

            private PromotionType() {
            }

            public static final String THONG_BAO = "THONG_BAO";
            public static final String UU_DAI = "UU_DAI";
        }

        public static class PromotionAnnounceMethod {

            private PromotionAnnounceMethod() {
            }

            public static final String ALL = "ALL";
            public static final String EMAIL = "EMAIL";
            public static final String NOTIFICATION = "NOTIFICATION";
        }

        public static class DefaultValue {
            public static final LinkedHashMap<String, String> UNIT_EXP = new LinkedHashMap<>() {{
                put("H", "Giờ");
                put("D", "Ngày");
                put("W", "Tuần");
                put("M", "Tháng");
                put("Y", "Năm");
            }};


            public static final String FORMAT_MULTI_GENRES = "({0})";
            public static final ArrayList<String> MULTI_PLAY = new ArrayList<>() {{
                add(null);
                add("true");
                add("false");
            }};


            public enum STRING_FORMAT_CASE {
                LOWER, UPPER
            }
        }

        public static class DetailMessages {

            public static final String PARSE_REQUEST_PARAMS_FAIL = "Parse request params fail";
            public static final String VALID_REQUEST_PARAMS_FAIL = "Valid request params fail";
            public static final String USER_BLOCKED = "User blocked";

        }
    }


