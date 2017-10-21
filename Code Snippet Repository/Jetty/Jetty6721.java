    @Test
    public void testFragmentedUnmaskedTextMessage()
    {
        WebSocketPolicy policy = new WebSocketPolicy(WebSocketBehavior.CLIENT);
        Parser parser = new UnitParser(policy);
        IncomingFramesCapture capture = new IncomingFramesCapture();
        parser.setIncomingFramesHandler(capture);

        ByteBuffer buf = ByteBuffer.allocate(16);
        BufferUtil.clearToFill(buf);

        // Raw bytes as found in RFC 6455, Section 5.7 - Examples
        // A fragmented unmasked text message (part 1 of 2 "Hel")
        buf.put(new byte[]
                { (byte)0x01, (byte)0x03, 0x48, (byte)0x65, 0x6c });

        // Parse #1
        BufferUtil.flipToFlush(buf,0);
        parser.parse(buf);

        // part 2 of 2 "lo" (A continuation frame of the prior text message)
        BufferUtil.flipToFill(buf);
        buf.put(new byte[]
                { (byte)0x80, 0x02, 0x6c, 0x6f });

        // Parse #2
        BufferUtil.flipToFlush(buf,0);
        parser.parse(buf);

        capture.assertNoErrors();
        capture.assertHasFrame(OpCode.TEXT,1);
        capture.assertHasFrame(OpCode.CONTINUATION,1);

        WebSocketFrame txt = capture.getFrames().poll();
        String actual = BufferUtil.toUTF8String(txt.getPayload());
        Assert.assertThat("TextFrame[0].data",actual,is("Hel"));
        txt = capture.getFrames().poll();
        actual = BufferUtil.toUTF8String(txt.getPayload());
        Assert.assertThat("TextFrame[1].data",actual,is("lo"));
    }
