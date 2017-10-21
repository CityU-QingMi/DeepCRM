    @Test
    public void testParseEmptyTextCase1_1_1()
    {

        ByteBuffer expected = ByteBuffer.allocate(5);

        expected.put(new byte[]
        { (byte)0x81, (byte)0x00 });

        expected.flip();

        Parser parser = new UnitParser(policy);
        IncomingFramesCapture capture = new IncomingFramesCapture();
        parser.setIncomingFramesHandler(capture);
        parser.parse(expected);

        capture.assertNoErrors();
        capture.assertHasFrame(OpCode.TEXT,1);

        Frame pActual = capture.getFrames().poll();
        Assert.assertThat("TextFrame.payloadLength",pActual.getPayloadLength(),is(0));
    }
