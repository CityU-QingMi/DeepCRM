    @Test
    public void testFailWhileBlocking() throws Exception
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
        incompleteFlush.set(false);

        Assert.assertEquals("How now br", endPoint.takeOutputString());

        String reason = "Failure";
        flusher.onFail(new IOException(reason));
        flusher.completeWrite();

        Assert.assertTrue(callback.isDone());
        Assert.assertFalse(incompleteFlush.get());

        try
        {
            callback.get();
            Assert.fail();
        }
        catch (ExecutionException e)
        {
            Throwable cause = e.getCause();
            Assert.assertTrue(cause instanceof IOException);
            Assert.assertEquals(reason, cause.getMessage());
        }
        Assert.assertEquals("", endPoint.takeOutputString());
        Assert.assertTrue(flusher.isIdle());
    }
