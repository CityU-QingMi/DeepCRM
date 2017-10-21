    public void dump()
    {
        System.err.printf("Captured %d incoming frames%n",frames.size());
        int i = 0;
        for (Frame frame : frames)
        {
            System.err.printf("[%3d] %s%n",i++,frame);
            System.err.printf("          payload: %s%n",BufferUtil.toDetailString(frame.getPayload()));
        }
    }
