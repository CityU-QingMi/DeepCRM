    @Test
    public void testUncoveredHttpMethodsDenied() throws Exception
    {
        try
        {
            _security.setDenyUncoveredHttpMethods(false);
            _security.setAuthenticator(new BasicAuthenticator());
            _server.start();
            
            //There are uncovered methods for GET/POST at url /*
            //without deny-uncovered-http-methods they should be accessible
            String response;
            response = _connector.getResponse("GET /ctx/index.html HTTP/1.0\r\n\r\n");
            assertThat(response,startsWith("HTTP/1.1 200 OK"));   
            
            //set deny-uncovered-http-methods true
            _security.setDenyUncoveredHttpMethods(true);
            
            //check they cannot be accessed
            response = _connector.getResponse("GET /ctx/index.html HTTP/1.0\r\n\r\n");
            assertTrue(response.startsWith("HTTP/1.1 403 Forbidden"));
        }
        finally
        {
            _security.setDenyUncoveredHttpMethods(false);
        }
        
    }
