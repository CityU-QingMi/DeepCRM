    private String getResponse(String request) throws IOException, InterruptedException
    {
        try (Socket socket = new Socket((String)null, _connector.getLocalPort()))
        {
            socket.setSoTimeout(10 * MAX_IDLE_TIME);
            socket.getOutputStream().write(request.getBytes(StandardCharsets.UTF_8));
            InputStream inputStream = socket.getInputStream();
            long start = System.currentTimeMillis();
            String response = IO.toString(inputStream);
            long timeElapsed = System.currentTimeMillis() - start;
            Assert.assertTrue("Time elapsed should be at least MAX_IDLE_TIME",timeElapsed > MAX_IDLE_TIME);
            return response;
        }
    }
