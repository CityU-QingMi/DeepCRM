    @Test
    public void testFor() throws Exception
    {
        String response=_connector.getResponse(
            "GET / HTTP/1.1\n"+
             "Host: myhost\n"+
             "X-Forwarded-For: 10.9.8.7,6.5.4.3\n"+
            "\n");
        assertThat(response, Matchers.containsString("200 OK"));
        assertEquals("http",_results.poll());
        assertEquals("myhost",_results.poll());
        assertEquals("80",_results.poll());
        assertEquals("10.9.8.7",_results.poll());
        assertEquals("0",_results.poll());
    }
