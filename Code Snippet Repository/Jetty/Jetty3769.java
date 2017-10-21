    @Test
    public void testRequestLine() throws Exception
    {
        _connector.getResponse("GET /foo?data=1 HTTP/1.0\nhost: host:80\n\n");
        String log = _log.exchange(null,5,TimeUnit.SECONDS);
        assertThat(log,containsString("GET /foo?data=1 HTTP/1.0\" 200 "));
        
        _connector.getResponse("GET //bad/foo?data=1 HTTP/1.0\n\n");
        log = _log.exchange(null,5,TimeUnit.SECONDS);
        assertThat(log,containsString("GET //bad/foo?data=1 HTTP/1.0\" 200 "));
                
        _connector.getResponse("GET http://host:80/foo?data=1 HTTP/1.0\n\n");
        log = _log.exchange(null,5,TimeUnit.SECONDS);
        assertThat(log,containsString("GET http://host:80/foo?data=1 HTTP/1.0\" 200 "));   
    }
