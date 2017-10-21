    @Test
    public void testEarlyEOF() throws Exception
    {
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                // Promise some content, then flush the headers, then fail to send the content.
                response.setContentLength(16);
                response.flushBuffer();
                throw new NullPointerException("Explicitly thrown by test");
            }
        });

        try (StacklessLogging stackless = new StacklessLogging(org.eclipse.jetty.server.HttpChannel.class))
        {
            client.newRequest("localhost", connector.getLocalPort())
                    .scheme(scheme)
                    .timeout(60, TimeUnit.SECONDS)
                    .send();
            Assert.fail();
        }
        catch (ExecutionException x)
        {
            // Expected.
        }
    }
