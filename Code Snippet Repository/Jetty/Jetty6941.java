    @Override
    public void outgoingFrame(Frame frame, WriteCallback callback, BatchMode batchMode)
    {
        ByteBuffer buf = ByteBuffer.allocate(Generator.MAX_HEADER_LENGTH + frame.getPayloadLength());
        generator.generateWholeFrame(frame,buf);
        BufferUtil.flipToFlush(buf,0);
        captured.add(buf);
        if (callback != null)
        {
            callback.writeSuccess();
        }
    }
