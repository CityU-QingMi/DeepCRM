    @Override
    protected void nextIncomingFrame(Frame frame)
    {
        if (frame.isFin() && !incomingContextTakeover)
        {
            LOG.debug("Incoming Context Reset");
            decompressCount.set(0);
            getInflater().reset();
        }
        super.nextIncomingFrame(frame);
    }
