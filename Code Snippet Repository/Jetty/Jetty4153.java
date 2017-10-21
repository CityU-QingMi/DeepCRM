    @Test
    public void testBadSplitPost() throws Exception
    {
        StringBuilder req = new StringBuilder();
        req.append("POST /post HTTP/1.1\r\n");
        req.append("Host: localhost\r\n");
        req.append("Connection: close\r\n");
        req.append("Transfer-Encoding: chunked\r\n");
        req.append("\r\n");
        req.append("6\r\n");
        req.append("Hello ");
        req.append("\r\n");

        try (StacklessLogging scope = new StacklessLogging(ServletHandler.class))
        {
            LocalConnector.LocalEndPoint endp=connector.executeRequest(req.toString());
            req.setLength(0);

            while(!posted.get())
                Thread.sleep(100);
            Thread.sleep(100);
            req.append("x\r\n");
            req.append("World\n");
            req.append("\r\n");
            req.append("0\r\n");
            req.append("\r\n");
            endp.addInput(req.toString());

            endp.waitUntilClosedOrIdleFor(1,TimeUnit.SECONDS);
            String resp = endp.takeOutputString();
            assertThat("resp", resp, containsString("HTTP/1.1 200 "));
            assertThat("resp", resp, not(containsString("\r\n0\r\n"))); // aborted
        }
        assertTrue(complete.await(5,TimeUnit.SECONDS));
        assertThat(ex0.get(),not(nullValue()));
        assertThat(ex1.get(),not(nullValue()));
    }
