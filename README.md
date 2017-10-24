# ClientServerStream

## Architecture
The server will listen for socket connections on `localhost:9989`.

## Server
Sets up a Server that serves the contents of a file called "islands\_in\_the\_stream.txt".

## Client
Client will connect to the server and transform the contents to a bag of words, counting the occurrence of every word and display the words with the most occurrence.

## Running the Server
```shell
$ cd src/
$ cp ../islands_in_the_stream .
$ javac Server.java && java Server .
```

## Running the Client
```shell
$ cd src/
$ javac Client.java && java Client .
```

