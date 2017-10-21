    @Test
    public void testContent() throws Exception
    {
        final AtomicInteger length=new AtomicInteger();

        _handler._checker = new RequestTester()
        {
            @Override
            public boolean check(HttpServletRequest request,HttpServletResponse response) throws IOException
            {
                int len=request.getContentLength();
                ServletInputStream in = request.getInputStream();
                for (int i=0;i<len;i++)
                {
                    int b=in.read();
                    if (b<0)
                        return false;
                }
                if (in.read()>0)
                    return false;

                length.set(len);
                return true;
            }
        };


        String content="";

        for (int l=0;l<1024;l++)
        {
            String request="POST / HTTP/1.1\r\n"+
            "Host: whatever\r\n"+
            "Content-Type: multipart/form-data-test\r\n"+
            "Content-Length: "+l+"\r\n"+
            "Connection: close\r\n"+
            "\r\n"+
            content;
            Log.getRootLogger().debug("test l={}",l);
            String response = _connector.getResponse(request);
            Log.getRootLogger().debug(response);
            assertThat(response, containsString(" 200 OK"));
            assertEquals(l,length.get());
            content+="x";
        }
    }
