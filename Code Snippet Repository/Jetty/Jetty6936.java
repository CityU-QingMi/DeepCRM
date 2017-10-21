    public void assertFrameCount(int expectedCount)
    {
        if (frames.size() != expectedCount)
        {
            // dump details
            System.err.printf("Expected %d frame(s)%n",expectedCount);
            System.err.printf("But actually captured %d frame(s)%n",frames.size());
            int i = 0;
            for (Frame frame : frames)
            {
                System.err.printf(" [%d] Frame[%s] - %s%n", i++, 
                        OpCode.name(frame.getOpCode()),
                        BufferUtil.toDetailString(frame.getPayload()));
            }
        }
        Assert.assertThat("Captured frame count",frames.size(),is(expectedCount));
    }
