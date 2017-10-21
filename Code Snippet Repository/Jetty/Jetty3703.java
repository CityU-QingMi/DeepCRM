    @Test
    public void testClosed() throws Exception
    {
        _test._close=true;
        String response = _local.getResponse("GET /ctx/include/path HTTP/1.1\r\nHost: localhost\r\n\r\n");
        assertThat(response,containsString(" 200 OK"));
        assertThat(response,containsString("Write: 0"));
        assertThat(response,containsString("Write: 9"));
        assertThat(response,not(containsString("Written: true")));
    }
