    public void testExampleUsage() throws Exception
    {
        try(Socket socket = new Socket("www.google.com",80))
        {
            HttpTester.Request request = HttpTester.newRequest();
            request.setMethod("POST");
            request.setURI("/search");
            request.setVersion(HttpVersion.HTTP_1_0);
            request.put(HttpHeader.HOST,"www.google.com");
            request.put("Content-Type","application/x-www-form-urlencoded");
            request.setContent("q=jetty%20server");
            ByteBuffer output = request.generate();

            socket.getOutputStream().write(output.array(),output.arrayOffset()+output.position(),output.remaining());
            HttpTester.Input input = HttpTester.from(socket.getInputStream());
            HttpTester.Response response = HttpTester.parseResponse(input);
            System.err.printf("%s %s %s%n",response.getVersion(),response.getStatus(),response.getReason());
            for (HttpField field:response)
                System.err.printf("%s: %s%n",field.getName(),field.getValue());
            System.err.printf("%n%s%n",response.getContent());
        }
    }
