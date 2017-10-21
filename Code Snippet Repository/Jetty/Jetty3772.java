    @Test
    public void testAbsolute() throws Exception
    {
        _connector.getResponse(
            "GET http://hostname:8888/foo?name=value HTTP/1.1\n"+
            "Host: servername\n"+
            "\n");
        String log = _log.exchange(null,5,TimeUnit.SECONDS);
        assertThat(log,containsString("GET http://hostname:8888/foo?name=value"));
        assertThat(log,containsString(" 200 "));
    }
