    @Test
    public void testParseEmptyPingCase2_1()
    {
        ByteBuffer expected = ByteBuffer.allocate(5);

        expected.put(new byte[]
                { (byte)0x89, (byte)0x00 });

        expected.flip();

        Parser parser = new UnitParser(policy);
        IncomingFramesCapture capture = new IncomingFramesCapture();
        parser.setIncomingFramesHandler(capture);
        parser.parse(expected);

        capture.assertNoErrors();
        capture.assertHasFrame(OpCode.PING,1);

        Frame pActual = capture.getFrames().poll();
        Assert.assertThat("PingFrame.payloadLength",pActual.getPayloadLength(),is(0));
        Assert.assertEquals("PingFrame.payload",0,pActual.getPayloadLength());
    }
