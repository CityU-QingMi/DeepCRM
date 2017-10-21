    @Test
    public void testConcurrentWriteAndOnFail() throws Exception
    {
        ByteArrayEndPoint endPoint = new ByteArrayEndPoint(new byte[0], 16);

        WriteFlusher flusher = new WriteFlusher(endPoint)
        {
            @Override
            protected ByteBuffer[] flush(ByteBuffer[] buffers) throws IOException
            {
                ByteBuffer[] result = super.flush(buffers);
                boolean notified = onFail(new Throwable());
                Assert.assertFalse(notified);
                return result;
            }

            @Override
            protected void onIncompleteFlush()
            {
            }
        };

        FutureCallback callback = new FutureCallback();
        flusher.write(callback, BufferUtil.toBuffer("foo"));

        // Callback must be successfully completed.
        callback.get(1, TimeUnit.SECONDS);
        // Flusher must be idle - not failed - since the write succeeded.
        Assert.assertTrue(flusher.isIdle());
    }
