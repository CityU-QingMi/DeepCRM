    @Test
    public void testSslCertificate() throws Exception
    {
        _customizer.setSslIsSecure(false);
        String response=_connector.getResponse(
             "GET / HTTP/1.1\n"+
             "Host: myhost\n"+
             "Proxy-auth-cert: Wibble\n"+
             "\n");
        
        assertThat(response, Matchers.containsString("200 OK"));
        assertEquals("http",_results.poll());
        assertEquals("myhost",_results.poll());
        assertEquals("80",_results.poll());
        assertEquals("0.0.0.0",_results.poll());
        assertEquals("0",_results.poll());
        assertFalse(_wasSecure.get());
        assertEquals("Wibble",_sslCertificate.get());
        
      
        _customizer.setSslIsSecure(true);  
        response=_connector.getResponse(
             "GET / HTTP/1.1\n"+
             "Host: myhost\n"+
             "Proxy-auth-cert: 0123456789abcdef\n"+
             "\n");
        
        assertThat(response, Matchers.containsString("200 OK"));
        assertEquals("https",_results.poll());
        assertEquals("myhost",_results.poll());
        assertEquals("443",_results.poll());
        assertEquals("0.0.0.0",_results.poll());
        assertEquals("0",_results.poll());
        assertTrue(_wasSecure.get());
        assertEquals("0123456789abcdef",_sslCertificate.get());
    }
