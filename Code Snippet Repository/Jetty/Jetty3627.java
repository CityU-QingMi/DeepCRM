    @Test
    public void testMultiPartNoConfig() throws Exception
    {
        _handler._checker = new RequestTester()
        {
            @Override
            public boolean check(HttpServletRequest request,HttpServletResponse response)
            {
                try
                {
                    request.getPart("stuff");
                    return false;
                }
                catch (IllegalStateException e)
                {
                    //expected exception because no multipart config is set up
                    assertTrue(e.getMessage().startsWith("No multipart config"));
                    return true;
                }
                catch (Exception e)
                {
                    return false;
                }
            }
        };

        String multipart =  "--AaB03x\r\n"+
        "content-disposition: form-data; name=\"field1\"\r\n"+
        "\r\n"+
        "Joe Blow\r\n"+
        "--AaB03x\r\n"+
        "content-disposition: form-data; name=\"stuff\"\r\n"+
        "Content-Type: text/plain;charset=ISO-8859-1\r\n"+
        "\r\n"+
        "000000000000000000000000000000000000000000000000000\r\n"+
        "--AaB03x--\r\n";

        String request="GET / HTTP/1.1\r\n"+
        "Host: whatever\r\n"+
        "Content-Type: multipart/form-data; boundary=\"AaB03x\"\r\n"+
        "Content-Length: "+multipart.getBytes().length+"\r\n"+
        "Connection: close\r\n"+
        "\r\n"+
        multipart;

        String responses=_connector.getResponse(request);
        assertTrue(responses.startsWith("HTTP/1.1 200"));
    }
