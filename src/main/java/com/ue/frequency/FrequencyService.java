package com.ue.frequency;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FrequencyService {
    public Map<Character, Long> getFrequency(String str) {
        return str.chars()
                .mapToObj(ch -> (char)ch)
                .collect(Collectors.groupingBy(ch -> ch, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> y, LinkedHashMap::new));
    }
}
