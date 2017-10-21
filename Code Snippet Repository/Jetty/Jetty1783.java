    @Test
    public void testPendingWriteDoesNotStoreConsumedBuffers() throws Exception
    {
        ByteArrayEndPoint endPoint = new ByteArrayEndPoint(new byte[0], 10);

        int toWrite = endPoint.getOutput().capacity();
        byte[] chunk1 = new byte[toWrite / 2];
        Arrays.fill(chunk1, (byte)1);
        ByteBuffer buffer1 = ByteBuffer.wrap(chunk1);
        byte[] chunk2 = new byte[toWrite];
        Arrays.fill(chunk1, (byte)2);
        ByteBuffer buffer2 = ByteBuffer.wrap(chunk2);

        AtomicBoolean incompleteFlush = new AtomicBoolean();
        WriteFlusher flusher = new WriteFlusher(endPoint)
        {
            @Override
            protected void onIncompleteFlush()
            {
                incompleteFlush.set(true);
            }
        };


        flusher.write(Callback.NOOP, buffer1, buffer2);
        Assert.assertTrue(incompleteFlush.get());
        Assert.assertFalse(buffer1.hasRemaining());

        // Reuse buffer1
        buffer1.clear();
        Arrays.fill(chunk1, (byte)3);
        int remaining1 = buffer1.remaining();

        // Complete the write
        endPoint.takeOutput();
        flusher.completeWrite();

        // Make sure buffer1 is unchanged
        Assert.assertEquals(remaining1, buffer1.remaining());
    }
