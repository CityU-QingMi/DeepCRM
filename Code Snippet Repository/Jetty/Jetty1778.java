    @Test
    public void testClosedNoBlocking() throws Exception
    {
        ByteArrayEndPoint endPoint = new ByteArrayEndPoint(new byte[0], 16);
        endPoint.close();

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
        flusher.write(callback, BufferUtil.toBuffer("foo"));

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
            Assert.assertThat(cause.getMessage(), Matchers.containsString("CLOSED"));
        }
        Assert.assertEquals("", endPoint.takeOutputString());
        Assert.assertTrue(flusher.isIdle());
    }
