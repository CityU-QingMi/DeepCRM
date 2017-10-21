    @Test
    public void testCompress_TimeTimeTime()
    {
        // What pywebsocket produces for "time:", "time:", "time:"
        String expected[] = new String[]
                {"2AC9CC4DB50200", "2A01110000", "02130000"};

        // Lets see what we produce
        CapturedHexPayloads capture = new CapturedHexPayloads();
        DeflateFrameExtension ext = new DeflateFrameExtension();
        init(ext);
        ext.setNextOutgoingFrames(capture);

        ext.outgoingFrame(new TextFrame().setPayload("time:"), null, BatchMode.OFF);
        ext.outgoingFrame(new TextFrame().setPayload("time:"), null, BatchMode.OFF);
        ext.outgoingFrame(new TextFrame().setPayload("time:"), null, BatchMode.OFF);

        List<String> actual = capture.getCaptured();

        Assert.assertThat("Compressed Payloads", actual, contains(expected));
    }
