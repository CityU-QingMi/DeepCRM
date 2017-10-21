    @Test
    public void testRFC7239_Examples_7_5() throws Exception
    {
        _connector.getResponse(
            "GET / HTTP/1.1\n"+
             "Host: myhost\n"+
             "Forwarded: for=192.0.2.43,for=198.51.100.17;by=203.0.113.60;proto=http;host=example.com\n"+
            "\n");

        assertEquals("http",_results.poll());
        assertEquals("example.com",_results.poll());
        assertEquals("80",_results.poll());
        assertEquals("192.0.2.43",_results.poll());
        assertEquals("0",_results.poll());
    }
