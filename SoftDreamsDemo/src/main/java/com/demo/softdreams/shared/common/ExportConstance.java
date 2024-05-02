package com.demo.softdreams.shared.common;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExportConstance {

    private ExportConstance() { }

    public static class CommonDisplay {

        private CommonDisplay() { }

        public static final String COL_DATE_DISPLAY = "Thời gian";
        public static final String COL_NameGame_DISPLAY = "      ";
    }






    public static class ExcelBlogReport {

        private ExcelBlogReport() {
        }

        public static final int COLUMN_INDEX_NAME_GAME = 0;
        public static final int COLUMN_INDEX_TOTAL_USER_VIEW = 1;
        public static final int COLUMN_INDEX_TOTAL_USER_PLAY = 2;
        public static final int COLUMN_INDEX_TOTAL_USER_PLAY_TIME = 3;
        public static final int COLUMN_INDEX_TOTAL_USER_NEW_VIEW = 4;
        public static final int COLUMN_INDEX_TOTAL_USER_NEW_PLAY = 5;
        public static final int COLUMN_INDEX_TOTAL_USER_NEW_PLAY_TIME = 6;

        public static final List<Integer> COL_SIZE = List.of(
                COLUMN_INDEX_NAME_GAME ,  COLUMN_INDEX_TOTAL_USER_VIEW,
                COLUMN_INDEX_TOTAL_USER_PLAY, COLUMN_INDEX_TOTAL_USER_PLAY_TIME, COLUMN_INDEX_TOTAL_USER_NEW_VIEW,COLUMN_INDEX_TOTAL_USER_NEW_PLAY,COLUMN_INDEX_TOTAL_USER_NEW_PLAY_TIME
        );

        public static final Map<Integer, String> COL_NAME = Map.of(
                COLUMN_INDEX_NAME_GAME, ExportConstance.CommonDisplay.COL_NameGame_DISPLAY,
                COLUMN_INDEX_TOTAL_USER_VIEW, "Số lượng lượt xem",
                COLUMN_INDEX_TOTAL_USER_PLAY, "Số lượng lượt chơi",
                COLUMN_INDEX_TOTAL_USER_PLAY_TIME, "Tổng số thời gian chơi",
                COLUMN_INDEX_TOTAL_USER_NEW_VIEW, "TB mới xem",
                COLUMN_INDEX_TOTAL_USER_NEW_PLAY, "TB mới chơi",
                COLUMN_INDEX_TOTAL_USER_NEW_PLAY_TIME, "Tổng số thời gian TB mới chơi"
        );
    }



    public static class SheetExcel {

        private SheetExcel() {
        }

        public static final int EXCEL_USER_UPLOAD_GAME = 1;
        public static final int EXCEL_REPORT_USER_GROWTH_UP = 2;
        public static final int EXCEL_REPORT_SUBSCRIPTION = 3;


        public static final int EXCEL_REPORT_LANDING_PAGE = 12;
        public static final Map<Integer, String> EXCEL_SHEET_NAME = Map.of(
                EXCEL_USER_UPLOAD_GAME, "Danh sách nhà cung cấp game",
                EXCEL_REPORT_USER_GROWTH_UP, "Báo cáo tăng trưởng người dùng",
                EXCEL_REPORT_SUBSCRIPTION, "Báo cáo gói cước"
        );

    }

    public static class ReportType {

        private ReportType() {
        }
        public static final String HOUR = "HOUR";
        public static final String DAY = "DAY";
        public static final String MONTH = "MONTH";
        public static final String YEAR = "YEAR";

        public static final String HOUR_DISPLAY = "Giờ";
        public static final String DAY_DISPLAY = "Ngày";
        public static final String MONTH_DISPLAY = "Tháng";
        public static final String YEAR_DISPLAY = "Năm";

        public static final String FORMAT_DAY = "dd/MM/yyyy";
        public static final String FORMAT_MONTH = "MM/yyyy";
        public static final String FORMAT_YEAR = "yyyy";
        public static final String FORMAT_HOUR = "hh";

        public static final Map<String, String> HASH_REPORT_TYPE = Map.of(
                DAY, FORMAT_DAY,
                MONTH, FORMAT_MONTH,
                YEAR, FORMAT_YEAR,
                HOUR, FORMAT_HOUR
        );

        public static final LinkedHashMap<String, String> HASH_REPORT_DISPLAY = new LinkedHashMap<>() {{
            put(DAY_DISPLAY, DAY);
            put(MONTH_DISPLAY, MONTH);
            put(YEAR_DISPLAY, YEAR);
//            put(HOUR_DISPLAY, HOUR);
        }};
    }
}

