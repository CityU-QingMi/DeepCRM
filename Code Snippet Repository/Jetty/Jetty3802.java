    @Test
    public void testWrongToken() throws Exception
    {
        start(null);

        HttpTester.Response response = shutdown("wrongToken");
        Assert.assertEquals(HttpStatus.UNAUTHORIZED_401, response.getStatus());

        Thread.sleep(1000);
        Assert.assertEquals(AbstractLifeCycle.STARTED, server.getState());
    }
