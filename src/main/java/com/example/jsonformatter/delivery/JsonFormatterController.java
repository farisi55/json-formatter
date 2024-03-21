package com.example.jsonformatter.delivery;

import com.example.jsonformatter.Utils.JsonMinify;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@Validated
@RequestMapping("/api/v1")
@Slf4j
public class JsonFormatterController {

    @PostMapping(value = "/json/lite")
    public ResponseEntity<?> receiveMessage (@RequestHeader(value = "ax-request-id", required = false) String requestId,
                                             @RequestHeader(value = "ax-request-at", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date requestDate,
                                             @RequestBody  String bodyRq,
                                             HttpServletRequest servletRequest)  {

        String result = new JsonMinify().minify(bodyRq);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }
}
