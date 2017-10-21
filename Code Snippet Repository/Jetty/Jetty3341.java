    @Test
    public void testOversizedBuffer() throws Exception
    {
        String response = null;
        try
        {
            int offset = 0;
            String cookie = "thisisastringthatshouldreachover1kbytes";
            for (int i=0;i<100;i++)
                cookie+="xxxxxxxxxxxx";
            response = connector.getResponse("GET / HTTP/1.1\r\n"+
                "Host: localhost\r\n" +
                "Cookie: "+cookie+"\r\n"+
                "\r\n"
             );
            checkContains(response, offset, "HTTP/1.1 431");
        }
        catch(Exception e)
        {
            if(response != null)
                System.err.println(response);
            throw e;
        }
    }
