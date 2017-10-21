    @Test
    public void testResponseInputStream() throws Exception
    {
        HttpClient client = new HttpClient();
        client.start();

        InputStreamResponseListener listener = new InputStreamResponseListener();
        // Send asynchronously with the InputStreamResponseListener
        client.newRequest("localhost", 8080).send(listener);

        // Call to the listener's get() blocks until the headers arrived
        Response response = listener.get(5, TimeUnit.SECONDS);

        // Now check the response information that arrived to decide whether to read the content
        if (response.getStatus() == 200)
        {
            byte[] buffer = new byte[256];
            try (InputStream input = listener.getInputStream())
            {
                while (true)
                {
                    int read = input.read(buffer);
                    if (read < 0)
                        break;
                    // Do something with the bytes just read
                }
            }
        }
        else
        {
            response.abort(new Exception());
        }
    }
