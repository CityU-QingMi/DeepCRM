    @Test
    public void testProxyUsage() throws Exception
    {
        // In proxies, we receive the headers but not the content, so we must be able to send the request with
        // a lazy content provider that does not block request.send(...)

        HttpClient client = new HttpClient();
        client.start();

        final AtomicBoolean sendContent = new AtomicBoolean(true);
        DeferredContentProvider async = new DeferredContentProvider(ByteBuffer.wrap(new byte[]{0, 1, 2}));
        client.newRequest("localhost", 8080)
                .content(async)
                .send(new Response.Listener.Adapter()
                {
                    @Override
                    public void onBegin(Response response)
                    {
                        if (response.getStatus() == 404)
                            sendContent.set(false);
                    }
                });

        Thread.sleep(100);

        if (sendContent.get())
            async.offer(ByteBuffer.wrap(new byte[]{0}));

        Thread.sleep(100);

        if (sendContent.get())
            async.offer(ByteBuffer.wrap(new byte[]{0}));

        Thread.sleep(100);

        async.close();
    }
