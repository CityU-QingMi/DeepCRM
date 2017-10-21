    public void dump()
    {
        System.out.printf("Captured %d outgoing writes%n",frames.size());
        for (int i = 0; i < frames.size(); i++)
        {
            Frame frame = frames.get(i);
            System.out.printf("[%3d] %s%n",i,frame);
            System.out.printf("      %s%n",BufferUtil.toDetailString(frame.getPayload()));
        }
    }
