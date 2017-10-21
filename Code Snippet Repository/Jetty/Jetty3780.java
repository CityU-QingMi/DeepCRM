    @Test
    public void testBigFile() throws Exception
    {
        _config.setOutputBufferSize(2048);

        HttpTester.Response response = HttpTester.parseResponse(
            _local.getResponse("GET /resource/big.txt HTTP/1.0\r\n\r\n"));
        assertThat(response.getStatus(),equalTo(200));
        assertThat(response.getContent(),startsWith("     1\tThis is a big file"));
        assertThat(response.getContent(),endsWith("   400\tThis is a big file" + LN));
    }
