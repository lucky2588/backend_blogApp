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

        public static final int COLUMN_INDEX_REPORT_TITLE = 0;
        public static final int COLUMN_INDEX_TOTAL_TB_ACTIVE_NEW = 1;
        public static final int COLUMN_INDEX_TOTAL_USER_VIEW = 2;


        public static final List<Integer> COL_SIZE_CCU = List.of(
                COLUMN_INDEX_REPORT_TITLE,COLUMN_INDEX_TOTAL_TB_ACTIVE_NEW,COLUMN_INDEX_TOTAL_USER_VIEW
        );
        public static final Map<Integer, String> COL_NAME_CCU = Stream.of(new Object[][] {
                { COLUMN_INDEX_REPORT_TITLE, "Title" },
                { COLUMN_INDEX_TOTAL_TB_ACTIVE_NEW, " View  " },
                { COLUMN_INDEX_TOTAL_USER_VIEW, " Comment " },

        }).collect(Collectors.toMap(data -> (Integer) data[0], data -> (String) data[1]));
    }
    public static class ExcelTrafficUsage {
        private ExcelTrafficUsage() {
        }
        // for CCU
        public static final int COLUMN_INDEX_REPORT_DATE_TIME = 0;
        public static final int COLUMN_INDEX_TOTAL_TB_ACTIVE_NEW = 1;
        public static final int COLUMN_INDEX_TOTAL_USER_VIEW = 2;
        public static final int COLUMN_INDEX_TOTAL_USER_PLAY = 3;
        public static final int COLUMN_INDEX_TOTAL_CCU_IN_HOURS = 4;

        public static final List<Integer> COL_SIZE_CCU = List.of(
                COLUMN_INDEX_REPORT_DATE_TIME,COLUMN_INDEX_TOTAL_TB_ACTIVE_NEW,COLUMN_INDEX_TOTAL_USER_VIEW
                ,COLUMN_INDEX_TOTAL_USER_PLAY,COLUMN_INDEX_TOTAL_CCU_IN_HOURS
        );
        public static final Map<Integer, String> COL_NAME_CCU = Stream.of(new Object[][] {
                { COLUMN_INDEX_REPORT_DATE_TIME, "Khung Giờ" },
                { COLUMN_INDEX_TOTAL_TB_ACTIVE_NEW, "Lượt Đăng Kí" },
                { COLUMN_INDEX_TOTAL_USER_VIEW, "Lượt Xem" },
                { COLUMN_INDEX_TOTAL_USER_PLAY, "Lượt Chơi" },
                { COLUMN_INDEX_TOTAL_CCU_IN_HOURS, "CCU CAO ĐIỂM (TƯƠNG ĐỐI, TÍNH CÁC LƯỢT CHƠI CÓ CÙNG THỜI GIAN CHƠI)" },
        }).collect(Collectors.toMap(data -> (Integer) data[0], data -> (String) data[1]));
    }



    public static class SheetExcel {

        private SheetExcel() {
        }


        public static final int EXCEL_REPORT_TRAFFIC_USAGE = 1;



        public static final Map<Integer, String> EXCEL_SHEET_NAME = Map.of(
                EXCEL_REPORT_TRAFFIC_USAGE, "Báo cáo lượt tương tác Blog"
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

