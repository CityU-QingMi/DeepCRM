    @Override
    public Content readFrom(Content content)
    {
        _decoder.decodeChunks(content.getByteBuffer());
        final ByteBuffer chunk = _chunk;

        if (chunk == null)
            return null;

        return new Content(chunk)
        {
            @Override
            public void succeeded()
            {
                _decoder.release(chunk);
            }
        };
    }
