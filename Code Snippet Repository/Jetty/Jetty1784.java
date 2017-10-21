    @Test(expected = WritePendingException.class)
    public void testConcurrentWrites() throws Exception
    {
        ByteArrayEndPoint endPoint = new ByteArrayEndPoint(new byte[0], 16);

        CountDownLatch flushLatch = new CountDownLatch(1);
        WriteFlusher flusher = new WriteFlusher(endPoint)
        {
            @Override
            protected ByteBuffer[] flush(ByteBuffer[] buffers) throws IOException
            {
                try
                {
                    flushLatch.countDown();
                    Thread.sleep(2000);
                    return super.flush(buffers);
                }
                catch (InterruptedException x)
                {
                    throw new InterruptedIOException();
                }
            }

            @Override
            protected void onIncompleteFlush()
            {
            }
        };

        // Two concurrent writes.
        new Thread(() -> flusher.write(Callback.NOOP, BufferUtil.toBuffer("foo"))).start();
        Assert.assertTrue(flushLatch.await(1, TimeUnit.SECONDS));
        // The second write throws WritePendingException.
        flusher.write(Callback.NOOP, BufferUtil.toBuffer("bar"));
    }
