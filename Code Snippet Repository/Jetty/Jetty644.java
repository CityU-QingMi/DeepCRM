    @Test
    public void testRequestOutputStream() throws Exception
    {
        HttpClient client = new HttpClient();
        client.start();

        OutputStreamContentProvider content = new OutputStreamContentProvider();
        try (OutputStream output = content.getOutputStream())
        {
            client.newRequest("localhost", 8080)
                    .content(content)
                    .send(new Response.CompleteListener()
                    {
                        @Override
                        public void onComplete(Result result)
                        {
                            Assert.assertEquals(200, result.getResponse().getStatus());
                        }
                    });

            output.write(new byte[1024]);
            output.write(new byte[512]);
            output.write(new byte[256]);
            output.write(new byte[128]);
        }
    }
