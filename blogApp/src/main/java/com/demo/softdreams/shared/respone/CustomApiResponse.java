package com.demo.softdreams.shared.respone;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static com.demo.softdreams.shared.common.ResponseConstance.INTERNAL;
import static com.demo.softdreams.shared.common.ResponseConstance.MSG;

@Data
@EqualsAndHashCode
public class CustomApiResponse {
    public String responseMessage = MSG.get(INTERNAL);
    public int responseCode = INTERNAL;

//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    public LocalDateTime responseDate = LocalDateTime.now();

    @JsonIgnore
    public int responseStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();


}