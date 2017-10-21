    private synchronized String process(String content) throws IOException, InterruptedException
    {
        String request = "GET / HTTP/1.1\r\n" + "Host: localhost\r\n";

        if (content == null)
            request += "\r\n";
        else
            request += "Content-Length: " + content.length() + "\r\n" + "\r\n" + content;
        return getResponse(request);
    }
