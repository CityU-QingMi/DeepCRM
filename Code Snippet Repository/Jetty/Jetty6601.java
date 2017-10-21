    @Override
    public void incomingFrame(Frame frame)
    {
        // Incoming frames are always non concurrent because
        // they are read and parsed with a single thread, and
        // therefore there is no need for synchronization.

        // This extension requires the RSV1 bit set only in the first frame.
        // Subsequent continuation frames don't have RSV1 set, but are compressed.
        if (frame.getType().isData())
        {
            incomingCompressed = frame.isRsv1();
        }

        if (OpCode.isControlFrame(frame.getOpCode()) || !incomingCompressed)
        {
            nextIncomingFrame(frame);
            return;
        }
        
        ByteAccumulator accumulator = newByteAccumulator();
        
        try 
        {
            ByteBuffer payload = frame.getPayload();
            decompress(accumulator, payload);
            if (frame.isFin())
            {
                decompress(accumulator, TAIL_BYTES_BUF.slice());
            }
            
            forwardIncoming(frame, accumulator);
        }
        catch (DataFormatException e)
        {
            throw new BadPayloadException(e);
        }

        if (frame.isFin())
            incomingCompressed = false;
    }
