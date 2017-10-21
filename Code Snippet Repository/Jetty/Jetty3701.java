    @Test
    public void testExcludedByMime() throws Exception
    {
        _test._mimeType="text/excluded";
        String response = _local.getResponse("GET /ctx/include/path HTTP/1.1\r\nHost: localhost\r\n\r\n");
        assertThat(response,containsString(" 200 OK"));
        assertThat(response,containsString("Write: 0"));
        assertThat(response,containsString("Write: 7"));
        assertThat(response,not(containsString("Content-Length: ")));
        assertThat(response,not(containsString("Write: 8")));
        assertThat(response,not(containsString("Write: 9")));
        assertThat(response,not(containsString("Written: true")));
    }
