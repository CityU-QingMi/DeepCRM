    private void releaseBuffer()
    {
        if (buffer == null)
            throw new IllegalStateException();
        if (BufferUtil.hasContent(buffer))
            throw new IllegalStateException();
        HttpClient client = getHttpDestination().getHttpClient();
        ByteBufferPool bufferPool = client.getByteBufferPool();
        bufferPool.release(buffer);
        buffer = null;
    }
