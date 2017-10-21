    @Test
    public void testBadPost() throws Exception
    {
        StringBuilder req = new StringBuilder(16*1024);
        req.append("POST /post HTTP/1.1\r\n");
        req.append("Host: localhost\r\n");
        req.append("Transfer-Encoding: chunked\r\n");
        req.append("\r\n");
        // intentionally bad (not a valid chunked char here)
        for (int i=1024;i-->0;)
            req.append("xxxxxxxxxxxx");
        req.append("\r\n");
        req.append("\r\n");

        String resp = connector.getResponse(req.toString());
        assertThat(resp,startsWith("HTTP/1.1 200 OK")); // exception eaten by handler
        assertTrue(complete.await(5,TimeUnit.SECONDS));
        assertThat(ex0.get(),not(nullValue()));
        assertThat(ex1.get(),not(nullValue()));
    }
