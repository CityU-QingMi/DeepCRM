    @Test
    public void testCompleteBlocking() throws Exception
    {
        ByteArrayEndPoint endPoint = new ByteArrayEndPoint(new byte[0], 10);

        AtomicBoolean incompleteFlush = new AtomicBoolean();
        WriteFlusher flusher = new WriteFlusher(endPoint)
        {
            @Override
            protected void onIncompleteFlush()
            {
                incompleteFlush.set(true);
            }
        };

        FutureCallback callback = new FutureCallback();
        flusher.write(callback, BufferUtil.toBuffer("How now brown cow!"));

        Assert.assertFalse(callback.isDone());
        Assert.assertFalse(callback.isCancelled());

        Assert.assertTrue(incompleteFlush.get());

        try
        {
            callback.get(100, TimeUnit.MILLISECONDS);
            Assert.fail();
        }
        catch (TimeoutException x)
        {
            incompleteFlush.set(false);
        }

        Assert.assertEquals("How now br", endPoint.takeOutputString());

        flusher.completeWrite();

        Assert.assertTrue(callback.isDone());
        Assert.assertEquals("own cow!", endPoint.takeOutputString());
        Assert.assertFalse(incompleteFlush.get());
        Assert.assertTrue(flusher.isIdle());
    }
