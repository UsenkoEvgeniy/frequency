package com.ue.frequency;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class FrequencyController {

    private final FrequencyService frequencyService;

    @GetMapping("/freq")
    public Map<Character, Long> getFrequency(@RequestParam String str) {
        log.info("Get request for string {}", str);
        return frequencyService.getFrequency(str);
    }
}
