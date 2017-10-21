    @Test
    public void testWindowedGenerateWithMasking()
    {
        // A decent sized frame, with masking
        byte payload[] = new byte[10240];
        Arrays.fill(payload,(byte)0x55);

        byte mask[] = new byte[]
        { 0x2A, (byte)0xF0, 0x0F, 0x00 };

        WebSocketFrame frame = new BinaryFrame().setPayload(payload);
        frame.setMask(mask); // masking!

        // Generate
        int windowSize = 2929;
        WindowHelper helper = new WindowHelper(windowSize);
        ByteBuffer completeBuffer = helper.generateWindowed(frame);

        // Validate
        int expectedHeaderSize = 8;
        int expectedSize = payload.length + expectedHeaderSize;
        int expectedParts = 1;

        helper.assertTotalParts(expectedParts);
        helper.assertTotalBytes(payload.length + expectedHeaderSize);

        Assert.assertThat("Generated Buffer",completeBuffer.remaining(),is(expectedSize));

        // Parse complete buffer.
        WebSocketPolicy policy = WebSocketPolicy.newServerPolicy();
        Parser parser = new UnitParser(policy);
        IncomingFramesCapture capture = new IncomingFramesCapture();
        parser.setIncomingFramesHandler(capture);

        parser.parse(completeBuffer);

        // Assert validity of frame
        WebSocketFrame actual = capture.getFrames().poll();
        Assert.assertThat("Frame.opcode",actual.getOpCode(),is(OpCode.BINARY));
        Assert.assertThat("Frame.payloadLength",actual.getPayloadLength(),is(payload.length));

        // Validate payload contents for proper masking
        ByteBuffer actualData = actual.getPayload().slice();
        Assert.assertThat("Frame.payload.remaining",actualData.remaining(),is(payload.length));
        while (actualData.remaining() > 0)
        {
            Assert.assertThat("Actual.payload[" + actualData.position() + "]",actualData.get(),is((byte)0x55));
        }
    }
