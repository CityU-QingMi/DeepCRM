    @Test
    public void testHashDOSSize() throws Exception
    {        
        try (StacklessLogging stackless = new StacklessLogging(HttpChannel.class))
        {
            LOG.info("Expecting maxFormSize limit and too much data exceptions...");
            _server.setAttribute("org.eclipse.jetty.server.Request.maxFormContentSize",3396);
            _server.setAttribute("org.eclipse.jetty.server.Request.maxFormKeys",1000);

            StringBuilder buf = new StringBuilder(4000000);
            buf.append("a=b");
            // we will just create a lot of keys and make sure the limit is applied
            for (int i=0;i<500;i++)
                buf.append("&").append("K").append(i).append("=").append("x");
            buf.append("&c=d");

            _handler._checker = new RequestTester()
            {
                @Override
                public boolean check(HttpServletRequest request,HttpServletResponse response)
                {
                    return "b".equals(request.getParameter("a")) && request.getParameter("c")==null;
                }
            };

            String request="POST / HTTP/1.1\r\n"+
                    "Host: whatever\r\n"+
                    "Content-Type: "+MimeTypes.Type.FORM_ENCODED.asString()+"\r\n"+
                    "Content-Length: "+buf.length()+"\r\n"+
                    "Connection: close\r\n"+
                    "\r\n"+
                    buf;

            long start=System.currentTimeMillis();
            String rawResponse = _connector.getResponse(request);
            HttpTester.Response response = HttpTester.parseResponse(rawResponse);
            assertThat("Response.status", response.getStatus(), is(400));
            assertThat("Response body content", response.getContent(),containsString(BadMessageException.class.getName()));
            assertThat("Response body content", response.getContent(),containsString(IllegalStateException.class.getName()));
            long now=System.currentTimeMillis();
            assertTrue((now-start)<5000);
        }
    }
