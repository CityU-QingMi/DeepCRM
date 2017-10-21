    @Test
    public void testWriteMultipleBuffers() throws Exception
    {
        int bufsize = (int)(policy.getMaxTextMessageBufferSize() * 2.5);
        char buf[] = new char[bufsize];
        if (LOG.isDebugEnabled())
            LOG.debug("Buffer size: {}",bufsize);
        Arrays.fill(buf,'x');
        buf[bufsize - 1] = 'o'; // mark last entry for debugging

        try (MessageWriter stream = new MessageWriter(session))
        {
            stream.write(buf);
        }

        Assert.assertThat("Socket.messageQueue.size",socket.messageQueue.size(),is(1));
        String msg = socket.messageQueue.poll();
        String expected = new String(buf);
        Assert.assertThat("Message",msg,is(expected));
    }
