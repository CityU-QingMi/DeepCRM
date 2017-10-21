    protected void forwardIncoming(Frame frame, ByteAccumulator accumulator)
    {
        DataFrame newFrame = new DataFrame(frame);
        // Unset RSV1 since it's not compressed anymore.
        newFrame.setRsv1(false);

        ByteBuffer buffer = getBufferPool().acquire(accumulator.getLength(),false);
        try
        {
            BufferUtil.flipToFill(buffer);
            accumulator.transferTo(buffer);
            newFrame.setPayload(buffer);
            nextIncomingFrame(newFrame);
        }
        finally
        {
            getBufferPool().release(buffer);
        }
    }
