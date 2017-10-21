    @Test
    public void testManyGETs() throws Exception
    {
        LocalEndPoint endp = _connector.connect();
        endp.addInput(
            "GET /R1 HTTP/1.1\r\n"+
            "Host: localhost\r\n"+
            "\r\n"+
            "GET /R2 HTTP/1.1\r\n"+
            "Host: localhost\r\n"+
            "\r\n"+
            "GET /R3 HTTP/1.1\r\n"+
            "Host: localhost\r\n"+
            "\r\n"+
            "GET /R4 HTTP/1.1\r\n"+
            "Host: localhost\r\n"+
            "\r\n"+
            "GET /R5 HTTP/1.1\r\n"+
            "Host: localhost\r\n"+
            "\r\n"+
            "GET /R6 HTTP/1.1\r\n"+
            "Host: localhost\r\n"+
            "Connection: close\r\n"+
            "\r\n");
        
        String r="";
        
        for (String response=endp.getResponse();response!=null;response=endp.getResponse())
            r+=response;
        
        for (int i=1;i<=6;i++)
        {
            assertThat(r,containsString("HTTP/1.1 200 OK"));
            assertThat(r,containsString("pathInfo=/R"+i));
            r=r.substring(r.indexOf("</html>")+8);
        }
    }
