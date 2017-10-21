    @Test
    public void testRFC7239_Examples_7_4() throws Exception
    {
        _connector.getResponse(
            "GET / HTTP/1.1\n"+
             "Host: myhost\n"+
             "Forwarded: for=192.0.2.43, for=\"[2001:db8:cafe::17]\"\n"+
            "\n");

        assertEquals("http",_results.poll());
        assertEquals("myhost",_results.poll());
        assertEquals("80",_results.poll());
        assertEquals("192.0.2.43",_results.poll());
        assertEquals("0",_results.poll());
    }
