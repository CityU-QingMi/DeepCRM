    @Test
    public void testProto() throws Exception
    {
        String response=_connector.getResponse(
            "GET / HTTP/1.1\n"+
             "Host: myhost\n"+
             "X-Forwarded-Proto: foobar\n"+
             "Forwarded: proto=https\n"+
            "\n");
        assertThat(response, Matchers.containsString("200 OK"));
        assertEquals("https",_results.poll());
        assertEquals("myhost",_results.poll());
        assertEquals("443",_results.poll());
        assertEquals("0.0.0.0",_results.poll());
        assertEquals("0",_results.poll());
    }
