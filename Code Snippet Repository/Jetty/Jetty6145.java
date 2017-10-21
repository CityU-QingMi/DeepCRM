    @Override
    public void appendFrame(ByteBuffer payload, boolean isLast) throws IOException
    {
        if (finished)
        {
            throw new IOException("Cannot append to finished buffer");
        }
        if (payload == null)
        {
            driver.onPartialBinaryMessage(BufferUtil.EMPTY_BUFFER,isLast);
        }
        else
        {
            driver.onPartialBinaryMessage(payload,isLast);
        }
    }
