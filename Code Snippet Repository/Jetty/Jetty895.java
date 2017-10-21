    @Test
    public void testGETResponseWithoutContent() throws Exception
    {
        start(new EmptyServerHandler());

        for (int i = 0; i < 2; ++i)
        {
            Response response = client.GET(scheme + "://localhost:" + connector.getLocalPort());
            Assert.assertNotNull(response);
            Assert.assertEquals(200, response.getStatus());
        }
    }
