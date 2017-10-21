    @Override
    public void appendFrame(ByteBuffer payload, boolean isLast) throws IOException
    {
        // No decoders for Partial messages per JSR-356 (PFD1 spec)

        // Supported Partial<> Type #1: ByteBuffer
        if (msgWrapper.isMessageType(ByteBuffer.class))
        {
            partialHandler.onMessage(payload==null?BufferUtil.EMPTY_BUFFER:
                payload.slice(),isLast);
            return;
        }

        // Supported Partial<> Type #2: byte[]
        if (msgWrapper.isMessageType(byte[].class))
        {
            partialHandler.onMessage(payload==null?new byte[0]:
                BufferUtil.toArray(payload),isLast);
            return;
        }

        StringBuilder err = new StringBuilder();
        err.append(msgWrapper.getHandler().getClass());
        err.append(" does not implement an expected ");
        err.append(MessageHandler.Partial.class.getName());
        err.append(" of type ");
        err.append(ByteBuffer.class.getName());
        err.append(" or byte[]");
        throw new IllegalStateException(err.toString());
    }
