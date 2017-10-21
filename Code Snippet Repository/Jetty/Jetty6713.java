    @Test
    public void testBasicPingParsing()
    {
        ByteBuffer buf = ByteBuffer.allocate(16);
        BufferUtil.clearToFill(buf);
        buf.put(new byte[]
                { (byte)0x89, 0x05, 0x48, 0x65, 0x6c, 0x6c, 0x6f });
        BufferUtil.flipToFlush(buf,0);

        WebSocketPolicy policy = new WebSocketPolicy(WebSocketBehavior.CLIENT);
        Parser parser = new UnitParser(policy);
        IncomingFramesCapture capture = new IncomingFramesCapture();
        parser.setIncomingFramesHandler(capture);
        parser.parse(buf);

        capture.assertNoErrors();
        capture.assertHasFrame(OpCode.PING,1);
        PingFrame ping = (PingFrame)capture.getFrames().poll();

        String actual = BufferUtil.toUTF8String(ping.getPayload());
        Assert.assertThat("PingFrame.payload",actual,is("Hello"));
    }
