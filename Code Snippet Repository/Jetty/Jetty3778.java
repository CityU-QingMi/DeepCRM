    @Test
    public void testJettyDirListing() throws Exception
    {
        HttpTester.Response response = HttpTester.parseResponse(
            _local.getResponse("GET /resource/ HTTP/1.0\r\n\r\n"));
        assertThat(response.getStatus(),equalTo(200));
        assertThat(response.getContent(),containsString("jetty-dir.css"));
        assertThat(response.getContent(),containsString("<H1>Directory: /resource/"));
        assertThat(response.getContent(),containsString("big.txt"));
        assertThat(response.getContent(),containsString("bigger.txt"));
        assertThat(response.getContent(),containsString("directory"));
        assertThat(response.getContent(),containsString("simple.txt"));
    }
