    private BufferedReader readAndDiscardHTTPResponse(Socket socket) throws IOException
    {
        // Read and discard the HTTP response
        InputStream input = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        String line = reader.readLine();
        while (line != null)
        {
            if (line.length() == 0)
                break;
            line = reader.readLine();
        }
        // Now we can parse the event-source stream
        return reader;
    }
