    @Override
    public void incomingFrame(Frame frame)
    {
        // Incoming frames are always non concurrent because
        // they are read and parsed with a single thread, and
        // therefore there is no need for synchronization.

        if ( frame.getType().isControl() || !frame.isRsv1() || !frame.hasPayload() )
        {
            nextIncomingFrame(frame);
            return;
        }

        try
        {
            ByteAccumulator accumulator = newByteAccumulator();
            decompress(accumulator, frame.getPayload());
            decompress(accumulator, TAIL_BYTES_BUF.slice());
            forwardIncoming(frame, accumulator);
        }
        catch (DataFormatException e)
        {
            throw new BadPayloadException(e);
        }
    }
