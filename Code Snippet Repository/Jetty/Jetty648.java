    @Test
    public void testRequestListener() throws Exception
    {
        HttpClient client = new HttpClient();
        client.start();

        Response response = client.newRequest("localhost", 8080)
                // Add a request listener
                .listener(new Request.Listener.Adapter()
                {
                    @Override
                    public void onSuccess(Request request)
                    {
                    }
                }).send();
        Assert.assertEquals(200, response.getStatus());
    }
