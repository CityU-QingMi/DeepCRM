    @Test
    public void testGzipBomb() throws Exception
    {
        byte[] data = new byte[512*1024];
        Arrays.fill(data,(byte)'X');
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream output = new GZIPOutputStream(baos);
        output.write(data);
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
        // TODO need to test back pressure works

        assertThat(response.getStatus(),is(200));
        assertThat(response.getContentBytes().length,is(512*1024));
    }
