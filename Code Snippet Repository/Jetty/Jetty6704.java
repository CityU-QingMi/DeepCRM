    @Test
    public void testWindowedGenerate()
    {
        // A decent sized frame, no masking
        byte payload[] = new byte[10240];
        Arrays.fill(payload,(byte)0x44);

        WebSocketFrame frame = new BinaryFrame().setPayload(payload);

        // Generate
        int windowSize = 1024;
        WindowHelper helper = new WindowHelper(windowSize);
        ByteBuffer completeBuffer = helper.generateWindowed(frame);

        // Validate
        int expectedHeaderSize = 4;
        int expectedSize = payload.length + expectedHeaderSize;
        int expectedParts = 1;

        helper.assertTotalParts(expectedParts);
        helper.assertTotalBytes(payload.length + expectedHeaderSize);

        Assert.assertThat("Generated Buffer",completeBuffer.remaining(),is(expectedSize));
    }
