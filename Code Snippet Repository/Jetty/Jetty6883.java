    @Test
    public void testWriteMultipleBuffers() throws Exception
    {
        int bufsize = (int)(policy.getMaxBinaryMessageBufferSize() * 2.5);
        byte buf[] = new byte[bufsize];
        LOG.debug("Buffer sizes: max:{}, test:{}",policy.getMaxBinaryMessageBufferSize(),bufsize);
        Arrays.fill(buf,(byte)'x');
        buf[bufsize - 1] = (byte)'o'; // mark last entry for debugging

        try (MessageOutputStream stream = new MessageOutputStream(session))
        {
            stream.write(buf);
        }

        Assert.assertThat("Socket.messageQueue.size",socket.messageQueue.size(),is(1));
        String msg = socket.messageQueue.poll();
        Assert.assertThat("Message",msg,allOf(containsString("byte[" + bufsize + "]"),containsString("xxxo>>>")));
    }
