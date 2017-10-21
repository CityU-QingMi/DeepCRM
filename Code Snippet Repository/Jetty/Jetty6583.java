    @Override
    public void incomingFrame(Frame frame)
    {
        saveFrame(frame,false);
        try
        {
            nextIncomingFrame(frame);
        }
        catch (Throwable t)
        {
            IO.close(incomingChannel);
            incomingChannel = null;
            throw t;
        }
    }
