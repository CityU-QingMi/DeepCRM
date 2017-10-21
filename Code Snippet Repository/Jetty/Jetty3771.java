    @Test
    public void testHTTP11() throws Exception
    {
        _connector.getResponse(
            "GET /foo?name=value HTTP/1.1\n"+
            "Host: servername\n"+
            "\n");
        String log = _log.exchange(null,5,TimeUnit.SECONDS);
        assertThat(log,containsString("GET /foo?name=value"));
        assertThat(log,containsString(" 200 "));
    }
