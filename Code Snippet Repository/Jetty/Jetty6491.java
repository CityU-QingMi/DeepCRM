    public void generateWholeFrame(Frame frame, ByteBuffer buf)
    {
        buf.put(generateHeaderBytes(frame));
        if (frame.hasPayload())
        {
            if (readOnly)
            {
                buf.put(frame.getPayload().slice());
            }
            else
            {
                buf.put(frame.getPayload());
            }
        }
    }
