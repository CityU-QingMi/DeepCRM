    @Test
    public void testGzipRequest() throws Exception
    {
        String data = "Hello Nice World! ";
        for (int i = 0; i < 10; ++i)
             data += data;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream output = new GZIPOutputStream(baos);
        output.write(data.getBytes(StandardCharsets.UTF_8));
        output.close();
        byte[] bytes = baos.toByteArray();
        
        // generated and parsed test
        HttpTester.Request request = HttpTester.newRequest();
        HttpTester.Response response;

        request.setMethod("POST");
        request.setURI("/ctx/echo");
        request.setVersion("HTTP/1.0");
        request.setHeader("Host","tester");
        request.setHeader("Content-Type","text/plain");
        request.setHeader("Content-Encoding","gzip");
        request.setContent(bytes);

        response = HttpTester.parseResponse(_connector.getResponse(request.generate()));

        assertThat(response.getStatus(),is(200));
        assertThat(response.getContent(),is(data));

    }
