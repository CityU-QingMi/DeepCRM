    private void writeHTTPRequest(Socket socket, String servletPath) throws IOException
    {
        int serverPort = socket.getPort();
        OutputStream output = socket.getOutputStream();

        String handshake = "";
        handshake += "GET " + context.getContextPath() + servletPath + " HTTP/1.1\r\n";
        handshake += "Host: localhost:" + serverPort + "\r\n";
        handshake += "Accept: text/event-stream\r\n";
        handshake += "\r\n";
        output.write(handshake.getBytes(StandardCharsets.UTF_8));
        output.flush();
    }
