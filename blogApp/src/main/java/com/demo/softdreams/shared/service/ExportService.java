package com.demo.softdreams.shared.service;

import com.demo.softdreams.administrator.dto.blog.BlogReport;
import com.demo.softdreams.shared.respone.ReportBlog;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ExportService {



    Workbook getWorkbook(String filePath);

    boolean writeHeader(Sheet sheet, int rowIndex, int type);

    boolean writeMultipleHeader(Sheet[] sheet, int rowIndex, int type);

    boolean writeMultipleHeaderMail(Sheet[] sheets, int rowIndex, int type);

    CellStyle createStyleForHeader(Sheet sheet);

    boolean autoResizeColumn(Sheet sheet, int lastColumn);

    ByteArrayInputStream createOutput(Workbook workbook);





    ByteArrayInputStream writeExcelReportUsageTrafficCCU(List<BlogReport> rgr);
    void writeDataReportTrafficUsageCCU(BlogReport rgr, Row row);




}
