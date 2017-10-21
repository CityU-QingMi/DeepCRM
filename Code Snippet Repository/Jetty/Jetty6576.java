    private IncomingFrames getLastIncoming()
    {
        IncomingFrames last = nextIncoming;
        boolean done = false;
        while (!done)
        {
            if (last instanceof AbstractExtension)
            {
                last = ((AbstractExtension)last).getNextIncoming();
            }
            else
            {
                done = true;
            }
        }
        return last;
    }
