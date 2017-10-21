   @Test 
   public void testForwardNonUTF8() throws Exception
    {
        _contextHandler.addServlet(ForwardNonUTF8Servlet.class, "/ForwardServlet/*");
        _contextHandler.addServlet(AssertNonUTF8ForwardServlet.class, "/AssertForwardServlet/*");
        
        String expected=
            "HTTP/1.1 200 OK\r\n"+
            "Content-Type: text/html\r\n"+
            "Content-Length: 0\r\n"+
            "\r\n";
        String responses = _connector.getResponse("GET /context/ForwardServlet?do=assertforward&foreign=%d2%e5%ec%ef%e5%f0%e0%f2%f3%f0%e0&test=1 HTTP/1.0\n\n");

        assertEquals(expected, responses);
    }
