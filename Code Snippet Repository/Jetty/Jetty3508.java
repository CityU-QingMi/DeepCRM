    @Test
    public void testSuspendedPipeline() throws Exception
    {
        SuspendHandler suspend = new SuspendHandler();
        suspend.setSuspendFor(30000);
        suspend.setResumeAfter(1000);
        configureServer(suspend);

        long start = System.currentTimeMillis();
        Socket client = newSocket(_serverURI.getHost(), _serverURI.getPort());
        client.setSoTimeout(5000);
        try
        {
            OutputStream os = client.getOutputStream();

            // write an initial request
            os.write((
                    "GET / HTTP/1.1\r\n" +
                            "host: " + _serverURI.getHost() + ":" + _serverURI.getPort() + "\r\n" +
                            "\r\n"
            ).getBytes());
            os.flush();

            Thread.sleep(200);

            // write an pipelined request
            os.write((
                    "GET / HTTP/1.1\r\n" +
                            "host: " + _serverURI.getHost() + ":" + _serverURI.getPort() + "\r\n" +
                            "connection: close\r\n" +
                            "\r\n"
            ).getBytes());
            os.flush();

            String response = readResponse(client);
            assertThat(response, containsString("RESUMEDHTTP/1.1 200 OK"));
            assertThat((System.currentTimeMillis() - start), greaterThanOrEqualTo(1999L));

            // TODO This test should also check that that the CPU did not spin during the suspend.
        }
        finally
        {
            client.close();
        }
    }
