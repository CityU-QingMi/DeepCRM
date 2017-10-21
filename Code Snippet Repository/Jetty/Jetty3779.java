    @Test
    public void testHeaders() throws Exception
    {
        HttpTester.Response response = HttpTester.parseResponse(
            _local.getResponse("GET /resource/simple.txt HTTP/1.0\r\n\r\n"));
        assertThat(response.getStatus(),equalTo(200));
        assertThat(response.get(CONTENT_TYPE),equalTo("text/plain"));
        assertThat(response.get(LAST_MODIFIED),Matchers.notNullValue());
        assertThat(response.get(CONTENT_LENGTH),equalTo("11"));
        assertThat(response.get(SERVER),containsString("Jetty"));
        assertThat(response.getContent(),containsString("simple text"));
    }
