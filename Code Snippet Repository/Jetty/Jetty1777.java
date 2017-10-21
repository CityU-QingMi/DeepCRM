    @Test
    public void testConcurrentIncompleteFlushAndOnFail() throws Exception
    {
        int capacity = 8;
        ByteArrayEndPoint endPoint = new ByteArrayEndPoint(new byte[0], capacity);
        String reason = "the_reason";

        WriteFlusher flusher = new WriteFlusher(endPoint)
        {
            @Override
            protected void onIncompleteFlush()
            {
                onFail(new Throwable(reason));
            }
        };

        FutureCallback callback = new FutureCallback();
        byte[] content = new byte[capacity * 2];
        flusher.write(callback, BufferUtil.toBuffer(content));

        try
        {
            // Callback must be failed.
            callback.get(1, TimeUnit.SECONDS);
        }
        catch (ExecutionException x)
        {
            Assert.assertEquals(reason, x.getCause().getMessage());
        }
    }
