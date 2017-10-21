    @Test
    public void testUnconsumedTimeout() throws Exception
    {
        connector.setIdleTimeout(500);
        int offset=0;
        String requests=
        "GET /R1?read=4 HTTP/1.1\r\n"+
        "Host: localhost\r\n"+
        "Transfer-Encoding: chunked\r\n"+
        "Content-Type: text/plain; charset=utf-8\r\n"+
        "\r\n"+
        "5;\r\n"+
        "12345\r\n";

        long start=System.currentTimeMillis();
        String response = connector.getResponse(requests, 2000, TimeUnit.MILLISECONDS);
        if ((System.currentTimeMillis()-start)>=2000)
            Assert.fail();

        offset = checkContains(response,offset,"HTTP/1.1 200");
        offset = checkContains(response,offset,"pathInfo=/R1");
        offset = checkContains(response,offset,"1234");
        checkNotContained(response,offset,"56789");
    }
