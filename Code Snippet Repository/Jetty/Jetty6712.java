    @Test
    public void testWindowedParseLargeFrame()
    {
        // Create frames
        byte payload[] = new byte[65536];
        Arrays.fill(payload,(byte)'*');

        List<WebSocketFrame> frames = new ArrayList<>();
        TextFrame text = new TextFrame();
        text.setPayload(ByteBuffer.wrap(payload));
        text.setMask(Hex.asByteArray("11223344"));
        frames.add(text);
        frames.add(new CloseInfo(StatusCode.NORMAL).asFrame());

        // Build up raw (network bytes) buffer
        ByteBuffer networkBytes = UnitGenerator.generate(frames);

        // Parse, in 4096 sized windows
        WebSocketPolicy policy = new WebSocketPolicy(WebSocketBehavior.SERVER);
        Parser parser = new UnitParser(policy);
        IncomingFramesCapture capture = new IncomingFramesCapture();
        parser.setIncomingFramesHandler(capture);

        while (networkBytes.remaining() > 0)
        {
            ByteBuffer window = networkBytes.slice();
            int windowSize = Math.min(window.remaining(),4096);
            window.limit(windowSize);
            parser.parse(window);
            networkBytes.position(networkBytes.position() + windowSize);
        }

        capture.assertNoErrors();
        Assert.assertThat("Frame Count",capture.getFrames().size(),is(2));
        WebSocketFrame frame = capture.getFrames().poll();
        Assert.assertThat("Frame[0].opcode",frame.getOpCode(),is(OpCode.TEXT));
        ByteBuffer actualPayload = frame.getPayload();
        Assert.assertThat("Frame[0].payload.length",actualPayload.remaining(),is(payload.length));
        // Should be all '*' characters (if masking is correct)
        for (int i = actualPayload.position(); i < actualPayload.remaining(); i++)
        {
            Assert.assertThat("Frame[0].payload[i]",actualPayload.get(i),is((byte)'*'));
        }
    }
