    private void testCompleteWrite(boolean failBefore) throws Exception
    {
        ByteArrayEndPoint endPoint = new ByteArrayEndPoint(new byte[0], 16);
        endPoint.setGrowOutput(true);

        AtomicBoolean incompleteFlush = new AtomicBoolean();
        WriteFlusher flusher = new WriteFlusher(endPoint)
        {
            @Override
            protected void onIncompleteFlush()
            {
                incompleteFlush.set(true);
            }
        };

        if (failBefore)
            flusher.onFail(new IOException("Ignored because no operation in progress"));

        FutureCallback callback = new FutureCallback();
        flusher.write(callback, BufferUtil.toBuffer("How "), BufferUtil.toBuffer("now "), BufferUtil.toBuffer("brown "), BufferUtil.toBuffer("cow!"));

        Assert.assertTrue(callback.isDone());
        Assert.assertFalse(incompleteFlush.get());
        Assert.assertEquals("How now brown cow!", endPoint.takeOutputString());
        Assert.assertTrue(flusher.isIdle());
    }
