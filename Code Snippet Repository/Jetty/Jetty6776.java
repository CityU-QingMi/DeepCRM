    @Test
    public void testParseHelloPingCase2_2()
    {
        String message = "Hello, world!";
        byte[] messageBytes = message.getBytes();

        ByteBuffer expected = ByteBuffer.allocate(32);

        expected.put(new byte[]
                { (byte)0x89 });

        byte b = 0x00; // no masking
        b |= messageBytes.length & 0x7F;
        expected.put(b);
        expected.put(messageBytes);

        expected.flip();

        Parser parser = new UnitParser(policy);
        IncomingFramesCapture capture = new IncomingFramesCapture();
        parser.setIncomingFramesHandler(capture);
        parser.parse(expected);

        capture.assertNoErrors();
        capture.assertHasFrame(OpCode.PING,1);

        Frame pActual = capture.getFrames().poll();
        Assert.assertThat("PingFrame.payloadLength",pActual.getPayloadLength(),is(message.length()));
        Assert.assertEquals("PingFrame.payload",message.length(),pActual.getPayloadLength());
    }
