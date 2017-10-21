    @Test
    public void testRFC7239_Examples_4() throws Exception
    {
        String response=_connector.getResponse(
            "GET / HTTP/1.1\n"+
             "Host: myhost\n"+
             "Forwarded: for=\"_gazonk\"\n"+
             "Forwarded: For=\"[2001:db8:cafe::17]:4711\"\n"+
             "Forwarded: for=192.0.2.60;proto=http;by=203.0.113.43\n"+
             "Forwarded: for=192.0.2.43, for=198.51.100.17\n"+
            "\n");
        assertThat(response, Matchers.containsString("200 OK"));
        assertEquals("http",_results.poll());
        assertEquals("myhost",_results.poll());
        assertEquals("80",_results.poll());
        assertEquals("[2001:db8:cafe::17]",_results.poll());
        assertEquals("4711",_results.poll());
    }
