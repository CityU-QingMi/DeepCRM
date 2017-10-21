    @Test
    public void testParse65535ByteTextCase1_1_6()
    {
        int length = 65535;

        ByteBuffer expected = ByteBuffer.allocate(length + 5);

        expected.put(new byte[]
        { (byte)0x81 });
        byte b = 0x00; // no masking
        b |= 0x7E;
        expected.put(b);
        expected.put(new byte[]
        { (byte)0xff, (byte)0xff });

        for (int i = 0; i < length; ++i)
        {
            expected.put("*".getBytes());
        }

        expected.flip();
        WebSocketPolicy policy = new WebSocketPolicy(WebSocketBehavior.CLIENT);
        policy.setMaxTextMessageSize(length);
        Parser parser = new UnitParser(policy);
        IncomingFramesCapture capture = new IncomingFramesCapture();
        parser.setIncomingFramesHandler(capture);
        parser.parse(expected);

        capture.assertNoErrors();
        capture.assertHasFrame(OpCode.TEXT,1);

        Frame pActual = capture.getFrames().poll();
        Assert.assertThat("TextFrame.payloadLength",pActual.getPayloadLength(),is(length));
        // Assert.assertEquals("TextFrame.payload",length,pActual.getPayloadData().length);
    }
