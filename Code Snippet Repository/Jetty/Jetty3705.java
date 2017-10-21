    @Test
    public void testBufferSizeBig() throws Exception
    {
        _test._bufferSize=4096;
        String response = _local.getResponse("GET /ctx/include/path HTTP/1.1\r\nHost: localhost\r\n\r\n");
        assertThat(response,containsString(" 200 OK"));
        assertThat(response,containsString("Content-Length: "));
        assertThat(response,containsString("Write: 0"));
        assertThat(response,containsString("Write: 9"));
        assertThat(response,containsString("Written: true"));
    }
