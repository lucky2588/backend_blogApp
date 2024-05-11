package com.demo.softdreams.administrator.service.impl;
import com.demo.softdreams.administrator.dto.blog.BlogReport;
import com.demo.softdreams.administrator.dto.blog.BlogsReport;
import com.demo.softdreams.core.entites.Blog;
import com.demo.softdreams.core.exception.BadResquestException;
import com.demo.softdreams.core.respository.BlogRepository;
import com.demo.softdreams.shared.common.SharedConstance;
import com.demo.softdreams.shared.respone.ExportData;
import com.demo.softdreams.shared.respone.ReportBlog;
import com.demo.softdreams.shared.service.ExportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.io.ByteArrayInputStream;
import java.util.List;



@Service
@Slf4j
@RequiredArgsConstructor
public class ExportDataSerivce {
    private final RabbitTemplate rabbitTemplate;
    private final ExportService exportService;
    private final ObjectMapper objectMapper;
    @Autowired
    private BlogRepository dataSource;

//    @RabbitListener(queues = "${rabbitmq.queue.name}")
//    @Transactional
    public void receivedMessage(String messeage){
        InputStreamResource isr = null;
        ByteArrayInputStream byteArrayInputStream;
        List<Object[]> queryRes;
        List<ReportBlog> mappedList;
        String orderBy;
        Query mainQuery;
        log.info("Info Data " +messeage);
        ExportData listExport = new ExportData();
        String replaceMesss = messeage.replace("\\", "");  // docs : https://www.baeldung.com/jackson-exception
        log.info("Messeage After : "+replaceMesss);
//        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        try {
            listExport  =  objectMapper.readValue(messeage,ExportData.class);
        } catch (JsonProcessingException exception) {
           throw new BadResquestException("Can't parse Json to Obj");
       }
        log.info("Enddd " +listExport);
//        if(listExport.getTypeReport().equals(SharedConstance.Admin.REPORT_BLOG)
//                && listExport.getTypeRole().equals(SharedConstance.Role.ROLE_ADMIN)){
//            try {
//                List<BlogReport> blogs = exportDataBlog();
//                byteArrayInputStream = exportService.writeExcelReportUsageTrafficCCU(blogs);
//                isr = new InputStreamResource(byteArrayInputStream);
//                isr.getContentAsByteArray();
//            } catch (Exception e) {
//                log.warn("Error at exportReportGame function with message: {}", e.getMessage());
//            }
//        }
        exportDataBlog();
//        return isr;
        log.info(" log mess "+listExport);
    }




   public List<BlogReport> exportDataBlog(){
       //            orderBy = MessageFormat.format(QueryConstance.CommonQuery.ORDER_BY, "timeLine DESC");
//            mainQuery = entityManager.createNativeQuery("select * form blog b");
//            queryRes = mainQuery.getResultList();
//            log.info("Execute query get Blog report successful");
//            mappedList = queryRes.stream().map(o -> new ReportBlog(
//                     o[0].toString(),
//                    Long.parseLong(o[1].toString()),
//                    Long.parseLong(o[2].toString())
//            )).collect(Collectors.toList());
//            dataSource.getReport();
      return dataSource.getReport();
    }






}
