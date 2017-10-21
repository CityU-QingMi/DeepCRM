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

        onEvent.getPolicy().assertValidTextMessageSize(size + payload.remaining());
        size += payload.remaining();

        // allow for fast fail of BAD utf (incomplete utf will trigger on messageComplete)
        this.utf.append(payload);
    }
