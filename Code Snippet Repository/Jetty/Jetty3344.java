    @Test
    public void testOversizedResponse() throws Exception
    {
        String str = "thisisastringthatshouldreachover1kbytes-";
        for (int i=0;i<500;i++)
            str+="xxxxxxxxxxxx";
        final String longstr = str;
        final CountDownLatch checkError = new CountDownLatch(1);
        String response = null;
        server.stop();
        server.setHandler(new AbstractHandler.ErrorDispatchHandler()
        {
            @Override
            protected void doNonErrorHandle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                response.setHeader(HttpHeader.CONTENT_TYPE.toString(),MimeTypes.Type.TEXT_HTML.toString());
                response.setHeader("LongStr", longstr);
                PrintWriter writer = response.getWriter();
                writer.write("<html><h1>FOO</h1></html>");
                writer.flush();
                if (writer.checkError())
                    checkError.countDown();
                response.flushBuffer();
            }
        });
        server.start();

        Logger logger = Log.getLogger(HttpChannel.class);
        try (StacklessLogging stackless = new StacklessLogging(logger))
        {
            logger.info("Expect IOException: Response header too large...");
            response = connector.getResponse("GET / HTTP/1.1\r\n"+
                "Host: localhost\r\n" +
                "\r\n"
             );

            checkContains(response, 0, "HTTP/1.1 500");
            assertTrue(checkError.await(1,TimeUnit.SECONDS));
        }
        catch(Exception e)
        {
            if(response != null)
                System.err.println(response);
            throw e;
        }
    }
