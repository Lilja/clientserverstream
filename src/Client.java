import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.*;

class Word {
    public int occurance;
    public String word;

    Word(int occurance, String word) {
        this.occurance=occurance;
        this.word=word;
    }

    public int compareTo(Word w) {
        if (this.occurance == w.occurance) {
           return 0;
        } else if (this.occurance > w.occurance) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return this.word + ":" + this.occurance;
    }

    public static Comparator<Word> WordOccuranceComparator = (o1, o2) -> o1.compareTo(o2);
}

public class Client {

    public static void main(String[] args) {
        Socket clientSocket = null;
        BufferedReader br   = null;
        PrintStream ps      = null;
        try {
            clientSocket = new Socket("localhost", 9989);
            ps = new PrintStream(clientSocket.getOutputStream(), true);
            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ps.print("WORDS");
            List<String> payload = bufreadToList(br);
            wordToOccurrence(payload);
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (clientSocket != null) {
                    clientSocket.close();
                }
                if (br != null) {
                    br.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<String> bufreadToList(BufferedReader br) {
        List<String> words = new ArrayList<>();
        String line;
        try {
            while ((line = br.readLine()) != null) {
                Arrays.stream(line.split(" ")).forEach(w->{
                    if (!w.trim().equals(""))
                        words.add(w);
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    public static List<Word> wordToOccurrence(List<String> payload) {
        // Create distinct word list
        Set<String> distinctWords = new HashSet<>(payload);
        List<Word> wordOccurrenceList = new ArrayList<>(distinctWords.size());

        // For every distinct word in the stream, count occurrence and map it to the word.
        for (String distinctWord : distinctWords) {
           wordOccurrenceList.add(new Word(Collections.frequency(payload, distinctWord), distinctWord));
        }

        // Sort so every word is based of on the occurrence
        wordOccurrenceList.sort(Word.WordOccuranceComparator);

        // If the list doesn't have 10 array slots, return the maximum of what it's got.
        int size = 10;
        if (wordOccurrenceList.size() < 10) {
            size = wordOccurrenceList.size();
        }

        return wordOccurrenceList.subList(0, size);
    }

}
