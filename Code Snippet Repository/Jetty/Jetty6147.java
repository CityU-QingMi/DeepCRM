    @Override
    public void appendFrame(ByteBuffer payload, boolean isLast) throws IOException
    {
        if (finished)
        {
            throw new IOException("Cannot append to finished buffer");
        }
        
        String text = utf8Partial.toPartialString(payload);
        driver.onPartialTextMessage(text,isLast);
    }
