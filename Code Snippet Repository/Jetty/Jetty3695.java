    @Test
    public void testFlushEmpty() throws Exception
    {
        _test._writes=1;
        _test._flush=true;
        _test._close=false;
        _test._content = new byte[0];
        String response = _local.getResponse("GET /ctx/include/path HTTP/1.1\r\nHost: localhost\r\n\r\n");
        assertThat(response,containsString(" 200 OK"));
        assertThat(response,containsString("Content-Length: "));
        assertThat(response,containsString("Write: 0"));
        assertThat(response,not(containsString("Write: 1")));
        assertThat(response,containsString("Written: true"));
    }
