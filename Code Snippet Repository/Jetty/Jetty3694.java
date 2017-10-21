    @Test
    public void testOne() throws Exception
    {
        _test._writes=1;
        String response = _local.getResponse("GET /ctx/include/path HTTP/1.1\r\nHost: localhost\r\n\r\n");
        assertThat(response,containsString(" 200 OK"));
        assertThat(response,containsString("Content-Length: "));
        assertThat(response,containsString("Write: 0"));
        assertThat(response,not(containsString("Write: 1")));
        assertThat(response,containsString("Written: true"));
    }
