    @Test
    public void testLegacyProto() throws Exception
    {
        String response=_connector.getResponse(
            "GET / HTTP/1.1\n"+
             "Host: myhost\n"+
             "X-Proxied-Https: on\n"+
            "\n");
        assertThat(response, Matchers.containsString("200 OK"));
        assertEquals("https",_results.poll());
        assertEquals("myhost",_results.poll());
        assertEquals("443",_results.poll());
        assertEquals("0.0.0.0",_results.poll());
        assertEquals("0",_results.poll());
        assertTrue(_wasSecure.get());
    }
