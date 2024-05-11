package com.demo.softdreams.shared.service.impl;

import com.demo.softdreams.administrator.dto.blog.BlogReport;
import com.demo.softdreams.core.entites.Blog;
import com.demo.softdreams.shared.common.ExportConstance;
import com.demo.softdreams.shared.respone.ReportBlog;
import com.demo.softdreams.shared.service.ExportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static com.demo.softdreams.shared.common.ExportConstance.SheetExcel.EXCEL_REPORT_TRAFFIC_USAGE;


@Service
@Slf4j
@RequiredArgsConstructor
public class ExportServiceImpl implements ExportService {
    private static final String PATTERN_LONG_NUMBER = "#,##0";
    private static final String PATTERN_DOUBLE_NUMBER = "#,##0.##";
    private static final short FORMAT_LONG_NUMBER = (short) BuiltinFormats.getBuiltinFormat(PATTERN_LONG_NUMBER);
    private static final String FILE_NAME_DEFAULT = "excel.xlsx";


    @Override
    public boolean writeMultipleHeader(Sheet[] sheets, int rowIndex, int type) {
        CellStyle cellStyle;
        Cell cell;
        Row row;
        boolean result = false;
        try {
            for (Sheet sheet: sheets) {
                cellStyle = createStyleForHeader(sheet);
                row = sheet.createRow(rowIndex);

                for (Integer i : ExportConstance.ExcelBlogReport.COL_SIZE_CCU) {
                    cell = row.createCell(i);
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(ExportConstance.ExcelBlogReport.COL_NAME_CCU.get(i));
                }
                result = true;
            }
        } catch (Exception ex) {
            log.warn("Error at writeHeader function with message: {}", ex.getMessage());
        }
        return result;
    }

    @Override
    public Workbook getWorkbook(String filePath) {
        Workbook workbook = null;
        try {
            if (filePath.endsWith("xlsx")) {
                workbook = new XSSFWorkbook();
            } else if (filePath.endsWith("xls")) {
                workbook = new HSSFWorkbook();
            } else {
                throw new IllegalArgumentException("The specified file is not Excel file");
            }
        } catch (Exception e) {
            log.warn("Error at getWorkbook function with message: {}", e.getMessage());
        }
        return workbook;
    }



    @Override
    public boolean writeHeader(Sheet sheet, int rowIndex, int type) {
        CellStyle cellStyle;
        Cell cell;
        Row row;
        boolean result = false;
        try {
            cellStyle = createStyleForHeader(sheet);
            row = sheet.createRow(rowIndex);

            switch (type) {
                case EXCEL_REPORT_TRAFFIC_USAGE:
                    for (Integer i : ExportConstance.ExcelTrafficUsage.COL_SIZE_CCU) {
                        cell = row.createCell(i);
                        cell.setCellStyle(cellStyle);
                        cell.setCellValue(ExportConstance.ExcelTrafficUsage.COL_NAME_CCU.get(i));
                    }
                    result = true;
                    break;
                default:
                    log.info("Cannot define report type");
                    break;
            }
        } catch (Exception ex) {
            log.warn("Error at writeHeader function with message: {}", ex.getMessage());
        }
        return result;
    }




    @Override
    public boolean writeMultipleHeaderMail(Sheet[] sheets, int rowIndex, int type) {
        CellStyle cellStyle;
        Cell cell;
        Row row;
        boolean result = false;
        try {
            for (Sheet sheet: sheets) {
                cellStyle = createStyleForHeader(sheet);
                row = sheet.createRow(rowIndex);

                for (Integer i : ExportConstance.ExcelBlogReport.COL_SIZE_CCU) {
                    cell = row.createCell(i);
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(ExportConstance.ExcelBlogReport.COL_NAME_CCU.get(i));
                }
                result = true;
            }
        } catch (Exception ex) {
            log.warn("Error at writeHeader function with message: {}", ex.getMessage());
        }
        return result;
    }

    @Override
    public CellStyle createStyleForHeader(Sheet sheet) {
        return null;
    }

    @Override
    public boolean autoResizeColumn(Sheet sheet, int lastColumn) {
        boolean result = false;
        try {
            for (int i = 0; i < lastColumn; i++) {
                sheet.autoSizeColumn(i);
            }
            result = true;
        } catch (Exception ex) {
            log.warn("Error at autoResizeColumn function with message: {}", ex.getMessage());
        }
        return result;
    }

    @Override
    public ByteArrayInputStream createOutput(Workbook workbook) {
        ByteArrayInputStream in = null;
        ByteArrayOutputStream out ;
        try {
            out = new ByteArrayOutputStream();
            workbook.write(out);
            in = new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            log.warn("Error at createOutput function with message: {}", e.getMessage());
        }
        return in;
    }




    @Override
    public ByteArrayInputStream writeExcelReportUsageTrafficCCU(List<BlogReport> rgr){
        boolean resultOfWriteHeader;
        ByteArrayInputStream byteArrayInputStream = null;
        int rowIndex = 0;
        Workbook workbook;
        Sheet sheet;
        try {
            workbook = getWorkbook(FILE_NAME_DEFAULT);
            if (workbook != null) {
                sheet = workbook.createSheet(ExportConstance.SheetExcel.EXCEL_SHEET_NAME.get(EXCEL_REPORT_TRAFFIC_USAGE));
                resultOfWriteHeader = writeHeader(sheet, rowIndex, EXCEL_REPORT_TRAFFIC_USAGE);
                if (resultOfWriteHeader) {
                    log.info("Write header blog  successful");
                    rowIndex++;
                    for (BlogReport r: rgr) {
                        Row row = sheet.createRow(rowIndex);
                        writeDataReportTrafficUsageCCU(r, row);
                        rowIndex++;
                    }
                    log.info("Write content blog successful");
                    byteArrayInputStream = createOutput(workbook);
                    log.info("Write byteArray blogsuccessful");
                }
            }
        } catch (Exception ex) {
            log.warn("Error at writeExcelReportUsageTrafficCCU function with message: {}", ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }
        return byteArrayInputStream;

    }

    @Override
    public void writeDataReportTrafficUsageCCU(BlogReport rgr, Row row) {
        Cell cell;
        CellStyle paymentCheckStyle;
        CellStyle alignCenter;
        try {
            paymentCheckStyle = row.getSheet().getWorkbook().createCellStyle();
            paymentCheckStyle.setAlignment(HorizontalAlignment.CENTER);
            alignCenter = row.getSheet().getWorkbook().createCellStyle();
            alignCenter.setAlignment(HorizontalAlignment.CENTER);

            cell = row.createCell(ExportConstance.ExcelBlogReport.COLUMN_INDEX_REPORT_TITLE);
            cell.setCellValue(rgr.getTitle());
            cell.setCellStyle(paymentCheckStyle);

            cell = row.createCell(ExportConstance.ExcelBlogReport.COLUMN_INDEX_TOTAL_TB_ACTIVE_NEW);
            cell.setCellValue(rgr.getView());
            cell.setCellStyle(paymentCheckStyle);

            cell = row.createCell(ExportConstance.ExcelBlogReport.COLUMN_INDEX_TOTAL_USER_VIEW);
            cell.setCellValue(rgr.getComment());
            cell.setCellStyle(paymentCheckStyle);


        } catch (Exception ex) {
            log.warn("Error at writeDataReportTrafficUsageCCU function with message: {}", ex.getMessage());
            throw new IllegalArgumentException(ex.getMessage());
        }
        }





    private String formatReportDate(Long reportDate, String truncType) {
        String localDateTime = "";
        try {
            if (ExportConstance.ReportType.HASH_REPORT_TYPE.containsKey(truncType)) {
                localDateTime = LocalDateTime
                        .ofInstant(Instant.ofEpochMilli(reportDate), ZoneId.systemDefault())
                        .format(DateTimeFormatter.ofPattern(ExportConstance.ReportType.HASH_REPORT_TYPE.get(truncType)));
                log.debug("Trunc type: {}, report date after format: {}", truncType, localDateTime);
            } else {
                log.warn("Cannot define trunc type, so report date is raw report parse to string: {}", reportDate);
                localDateTime = reportDate.toString();
            }
        } catch (Exception e) {
            log.warn("Error at formatReportDate function with message: {}", e.getMessage());
        }
        return localDateTime;
    }

    private String formatReportDate(Date reportDate) {
        String localDateTime = "";
        if (reportDate == null) return localDateTime;
        try {
            localDateTime = LocalDateTime
                    .ofInstant(Instant.ofEpochMilli(reportDate.getTime()), ZoneId.systemDefault())
                    .format(DateTimeFormatter.ofPattern(ExportConstance.ReportType.HASH_REPORT_TYPE.get("DAY")));
        } catch (Exception e) {
            log.warn("Error at formatReportDate function with message: {}", e.getMessage());
        }
        return localDateTime;
    }

    private String formatDateSilently(Date date, String format) {
        try {
            if (date == null) {
                return null;
            } else {
                if (format == null) {
                    format = "yyyy-MM-dd";
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                Instant instant = date.toInstant();
                LocalDateTime ldt = instant.atZone(ZoneId.of("GMT+7")).toLocalDateTime();
                return ldt.format(formatter);
            }
        } catch (Exception var5) {
            return null;
        }
    }

    private static String formatDecimal(Double value) {
        DecimalFormat df = new DecimalFormat(PATTERN_DOUBLE_NUMBER);
        return df.format(value);
    }

    private static String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }


}
