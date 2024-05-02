package com.demo.softdreams.shared.res;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    public LocalDateTime responseDate = LocalDateTime.now();

    @JsonIgnore
    public int responseStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
}