    private HttpTester.Response shutdown(String shutdownToken) throws IOException
    {
        try (Socket socket = new Socket("localhost", connector.getLocalPort()))
        {
            String request = "" +
                    "POST /shutdown?token=" + shutdownToken + " HTTP/1.1\r\n" +
                    "Host: localhost\r\n" +
                    "\r\n";
            OutputStream output = socket.getOutputStream();
            output.write(request.getBytes(StandardCharsets.UTF_8));
            output.flush();

            HttpTester.Input input = HttpTester.from(socket.getInputStream());
            return HttpTester.parseResponse(input);
        }
    }
