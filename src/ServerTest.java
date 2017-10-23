import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;

import static org.junit.Assert.*;

public class ServerTest {

    @Test
    public void testBufreadToStream() throws Exception {
        final String payload = "Apple";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        BufferedReader br = new BufferedReader(new StringReader(payload));
        Server.bufreadToStream(br, ps);
        assertEquals(payload, baos.toString().trim());
    }

    @Test
    public void testSendStuff() throws Exception {

    }

    @Test
    public void testFileToBufread() throws Exception {

    }
}