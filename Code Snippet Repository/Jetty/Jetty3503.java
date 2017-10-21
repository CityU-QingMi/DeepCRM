    @Test
    public void testCommittedError() throws Exception
    {
        CommittedErrorHandler handler = new CommittedErrorHandler();
        configureServer(handler);

        try (Socket client = newSocket(_serverURI.getHost(), _serverURI.getPort());
             StacklessLogging stackless = new StacklessLogging(HttpChannel.class))
        {
            ((AbstractLogger)Log.getLogger(HttpChannel.class)).info("Expecting exception after commit then could not send 500....");
            OutputStream os = client.getOutputStream();
            InputStream is = client.getInputStream();

            // Send a request
            os.write(("GET / HTTP/1.1\r\n" +
                    "Host: " + _serverURI.getHost() + ":" + _serverURI.getPort() + "\r\n" +
                    "\r\n"
            ).getBytes());
            os.flush();


            client.setSoTimeout(2000);
            String in = IO.toString(is);

            assertEquals(-1, is.read()); // Closed by error!

            assertThat(in,containsString("HTTP/1.1 200 OK"));
            assertTrue(in.indexOf("Transfer-Encoding: chunked") > 0);
            assertTrue(in.indexOf("Now is the time for all good men to come to the aid of the party") > 0);
            assertThat(in, Matchers.not(Matchers.containsString("\r\n0\r\n")));

            client.close();
            Thread.sleep(200);

            assertTrue(!handler._endp.isOpen());
        }
    }
