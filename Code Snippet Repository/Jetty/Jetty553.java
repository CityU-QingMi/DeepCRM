    @Test
    public void test_GET_ResponseWithoutContent() throws Exception
    {
        start(new EmptyServerHandler());

        Response response = client.GET(scheme + "://localhost:" + connector.getLocalPort());

        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }
