package com.ue.frequency;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class FrequencyServiceTest {
    FrequencyService frequencyService = new FrequencyService();

    @Test
    void getFrequency_returnEmptyMap_whenEmptyString() {
        assertTrue(frequencyService.getFrequency("").isEmpty(), "Not empty map on empty string");
    }

    @Test
    void getFrequency_returnExpectedCounts_whenValidString() {
        String str = "aaaaabcccc";
        Map<Character, Long> result = frequencyService.getFrequency(str);
        assertEquals(5, result.get('a'), "a must be 5");
        assertEquals(1, result.get('b'), "b must be 1");
        assertEquals(4, result.get('c'), "c must be 4");
    }

    @Test
    void getFrequency_returnExpectedSize_whenValidString() {
        String str = "aaaaabccccbb";
        Map<Character, Long> result = frequencyService.getFrequency(str);
        assertEquals(3, result.size(), "Result map must have 3 entries");
    }

    @Test
    void getFrequency_returnExpectedOrder_whenValidString() {
        String str = "aaaaabccccb";
        Map<Character, Long> result = frequencyService.getFrequency(str);
        Iterator<Map.Entry<Character, Long>> iterator = result.entrySet().iterator();
        assertEquals('a', iterator.next().getKey(), "First must be 'a'");
        assertEquals('c', iterator.next().getKey(), "Second must be 'c'");
        assertEquals('b', iterator.next().getKey(), "Third must be 'b'");
        assertThrows(NoSuchElementException.class, iterator::next, "Map has to contain only 3 entries");
    }
}