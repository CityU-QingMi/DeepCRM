    @Test
    public void testCase7_3_1ParseEmptyClose()
    {
        ByteBuffer expected = ByteBuffer.allocate(5);

        expected.put(new byte[]
                { (byte)0x88, (byte)0x00 });

        expected.flip();

        Parser parser = new UnitParser(policy);
        IncomingFramesCapture capture = new IncomingFramesCapture();
        parser.setIncomingFramesHandler(capture);
        parser.parse(expected);

        capture.assertNoErrors();
        capture.assertHasFrame(OpCode.CLOSE,1);

        Frame pActual = capture.getFrames().poll();
        Assert.assertThat("CloseFrame.payloadLength",pActual.getPayloadLength(),is(0));

    }
