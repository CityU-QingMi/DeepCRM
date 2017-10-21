    private OutgoingFrames getLastOutgoing()
    {
        OutgoingFrames last = nextOutgoing;
        boolean done = false;
        while (!done)
        {
            if (last instanceof AbstractExtension)
            {
                last = ((AbstractExtension)last).getNextOutgoing();
            }
            else
            {
                done = true;
            }
        }
        return last;
    }
