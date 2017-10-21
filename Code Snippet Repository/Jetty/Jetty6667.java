    @Override
    public void appendFrame(ByteBuffer payload, boolean isLast) throws IOException
    {
        if (finished)
        {
            throw new IOException("Cannot append to finished buffer");
        }

        if (payload == null)
        {
            // empty payload is valid
            return;
        }

        onEvent.getPolicy().assertValidBinaryMessageSize(size + payload.remaining());
        size += payload.remaining();

        BufferUtil.writeTo(payload,out);
    }
