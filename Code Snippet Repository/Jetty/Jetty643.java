    @Test
    public void testRequestInputStream() throws Exception
    {
        HttpClient client = new HttpClient();
        client.start();

        InputStream input = new ByteArrayInputStream("content".getBytes(StandardCharsets.UTF_8));

        ContentResponse response = client.newRequest("localhost", 8080)
                // Provide the content as InputStream
                .content(new InputStreamContentProvider(input))
                .send();

        Assert.assertEquals(200, response.getStatus());
    }
