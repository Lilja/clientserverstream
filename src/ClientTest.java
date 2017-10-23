import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ClientTest {

    @Test
    public void test() {
        List<String> words = new ArrayList<>();
        words.add("hello");
        words.add("hello");
        words.add("hello2");
        words.add("hello3");
        List<Word> wordsOcc = Client.wordToOccurrence(words);
        assertEquals(2, wordsOcc.get(0).occurance);
        assertEquals(1, wordsOcc.get(1).occurance);
    }

    @Test
    public void test2() {
        BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream("There's a feeling I get\nWhen I look to the west".getBytes())));
        List<String> strings = Client.bufreadToList(br);
        List<Word> wordsOcc = Client.wordToOccurrence(strings);
        assertEquals("I", wordsOcc.get(0).word);
        assertEquals(2, wordsOcc.get(0).occurance);
    }
}