    @Override
    protected void nextOutgoingFrame(Frame frame, WriteCallback callback, BatchMode batchMode)
    {
        if (frame.isFin() && !outgoingContextTakeover)
        {
            LOG.debug("Outgoing Context Reset");
            getDeflater().reset();
        }
        super.nextOutgoingFrame(frame, callback, batchMode);
    }
