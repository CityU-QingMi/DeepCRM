    @Test
    public void testWelcomeRedirect() throws Exception
    {
        try
        {
            _resourceHandler.setRedirectWelcome(true);
            HttpTester.Response response = HttpTester.parseResponse(
                _local.getResponse("GET /resource/directory/ HTTP/1.0\r\n\r\n"));
            assertThat(response.getStatus(),equalTo(302));
            assertThat(response.get(LOCATION),containsString("/resource/directory/welcome.txt"));
        }
        finally
        {
            _resourceHandler.setRedirectWelcome(false);
        }
    }
