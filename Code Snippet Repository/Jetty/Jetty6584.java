    @Override
    public void outgoingFrame(Frame frame, WriteCallback callback, BatchMode batchMode)
    {
        saveFrame(frame,true);
        try
        {
            nextOutgoingFrame(frame,callback,batchMode);
        }
        catch (Throwable t)
        {
            IO.close(outgoingChannel);
            outgoingChannel = null;
            throw t;
        }
    }
