    @Test
    public void testUnconsumedException() throws Exception
    {
        int offset=0;
        String requests="GET /R1?read=1&ISE=true HTTP/1.1\r\n"+
        "Host: localhost\r\n"+
        "Transfer-Encoding: chunked\r\n"+
        "Content-Type: text/plain; charset=utf-8\r\n"+
        "\r\n"+
        "5;\r\n"+
        "12345\r\n"+
        "5;\r\n"+
        "67890\r\n"+
        "0;\r\n" +
        "\r\n"+
        "GET /R2 HTTP/1.1\r\n"+
        "Host: localhost\r\n"+
        "Content-Type: text/plain; charset=utf-8\r\n"+
        "Content-Length: 10\r\n"+
        "\r\n"+
        "abcdefghij\r\n";

        Logger logger = Log.getLogger(HttpChannel.class);
        try (StacklessLogging stackless = new StacklessLogging(logger))
        {
            logger.info("EXPECTING: java.lang.IllegalStateException...");
            String response = connector.getResponse(requests);
            offset = checkContains(response,offset,"HTTP/1.1 500");
            offset = checkContains(response,offset,"Connection: close");
            checkNotContained(response,offset,"HTTP/1.1 200");
        }
    }
