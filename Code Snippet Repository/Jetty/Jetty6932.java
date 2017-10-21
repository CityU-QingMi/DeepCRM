    public void send(ByteBuffer buf) throws IOException
    {
        Assert.assertThat("Client connected",client.isConnected(),is(true));
        LOG.debug("Sending bytes {}",BufferUtil.toDetailString(buf));
        if (sendMode == SendMode.SLOW)
        {
            client.writeRawSlowly(buf,slowSendSegmentSize);
        }
        else
        {
            client.writeRaw(buf);
        }
    }
