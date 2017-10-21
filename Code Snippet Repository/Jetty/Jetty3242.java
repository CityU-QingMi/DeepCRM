    protected HttpTester.Response executeRequest() throws URISyntaxException, IOException
    {
        try(Socket socket = new Socket("localhost", connector.getLocalPort()))
        {
            socket.setSoTimeout((int)connector.getIdleTimeout());
            
            try(PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream())))
            {
                writer.write("GET / " + httpVersion + "\r\n");
                writer.write("Host: localhost\r\n");
                writer.write("\r\n");
                writer.flush();
    
                HttpTester.Response response = new HttpTester.Response();
                HttpTester.Input input = HttpTester.from(socket.getInputStream());
                HttpTester.parseResponse(input, response);
                
                if ("HTTP/1.1".equals(httpVersion)
                        && response.isComplete()
                        && response.get("content-length") == null
                        && response.get("transfer-encoding") == null
                        && !__noBodyCodes.contains(response.getStatus()))
                    assertThat("If HTTP/1.1 response doesn't contain transfer-encoding or content-length headers, " +
                            "it should contain connection:close", response.get("connection"), is("close"));
                return response;
            }
        }
    }
