import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket mySock = null;
        Socket clientSocket = null;
        BufferedReader br = null;
        OutputStream os = null;
        try {
            mySock = new ServerSocket(9989);
            System.out.println("Running a socket on localhost:9989");
            clientSocket = mySock.accept();
            System.out.println("Incoming socket connection");
            br = fileToBufread(new FileReader("islands_in_the_stream.txt"));
            os = clientSocket.getOutputStream();
            sendStuff(br, os);
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (os != null) {
                    os.close();
                }
                if (clientSocket != null) {
                    clientSocket.close();
                }
                if (mySock != null) {
                    mySock.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Done, shutting down server");
        }
    }

    public static void bufreadToStream(BufferedReader br, PrintStream ps) {
        String line;
        try {
            while ((line = br.readLine()) != null) {
               ps.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void sendStuff(BufferedReader br, OutputStream os) {
        PrintStream ps = new PrintStream(os);
        bufreadToStream(br, ps);
    }

    public static BufferedReader fileToBufread(FileReader f) {
        if (f != null) {
            return new BufferedReader(f);
        } else return null;
    }

}
