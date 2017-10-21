    @Test
    public void testSslSession() throws Exception
    {
        _customizer.setSslIsSecure(false);
        String response=_connector.getResponse(
             "GET / HTTP/1.1\n"+
             "Host: myhost\n"+
             "Proxy-Ssl-Id: Wibble\n"+
             "\n");
        
        assertThat(response, Matchers.containsString("200 OK"));
        assertEquals("http",_results.poll());
        assertEquals("myhost",_results.poll());
        assertEquals("80",_results.poll());
        assertEquals("0.0.0.0",_results.poll());
        assertEquals("0",_results.poll());
        assertFalse(_wasSecure.get());
        assertEquals("Wibble",_sslSession.get());
      
        _customizer.setSslIsSecure(true);  
        response=_connector.getResponse(
             "GET / HTTP/1.1\n"+
             "Host: myhost\n"+
             "Proxy-Ssl-Id: 0123456789abcdef\n"+
             "\n");
        
        assertThat(response, Matchers.containsString("200 OK"));
        assertEquals("https",_results.poll());
        assertEquals("myhost",_results.poll());
        assertEquals("443",_results.poll());
        assertEquals("0.0.0.0",_results.poll());
        assertEquals("0",_results.poll());
        assertTrue(_wasSecure.get());
        assertEquals("0123456789abcdef",_sslSession.get());
    }
