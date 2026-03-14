package io.krystof.spring_boot_hello;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HelloController {

    private final BuildProperties buildProperties;

    @GetMapping("/")
    public String index(@RequestParam(name = "delayInMs", required = false, defaultValue = "0") int delayInMs, HttpServletRequest request)  {
        StringBuilder sb = new StringBuilder();
        
        log.info("Received request with delay {} ms from {}", delayInMs, request.getRemoteAddr());

        sb.append("<html><body><pre>");
        sb.append("Greetings ").append(request.getRemoteAddr())
                .append(" from ").append(buildProperties.getVersion()).append("!\n");
        sb.append("</pre></body></html>");

        return sb.toString();
    }

}
