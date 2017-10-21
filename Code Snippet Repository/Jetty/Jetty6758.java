    @Test
    public void testParseEmptyBinaryCase1_2_1()
    {

        ByteBuffer expected = ByteBuffer.allocate(5);

        expected.put(new byte[]
                { (byte)0x82, (byte)0x00 });

        expected.flip();

        Parser parser = new UnitParser(policy);
        IncomingFramesCapture capture = new IncomingFramesCapture();
        parser.setIncomingFramesHandler(capture);
        parser.parse(expected);

        capture.assertNoErrors();
        capture.assertHasFrame(OpCode.BINARY,1);

        Frame pActual = capture.getFrames().poll();
        Assert.assertThat("BinaryFrame.payloadLength",pActual.getPayloadLength(),is(0));
        // Assert.assertNull("BinaryFrame.payload",pActual.getPayloadData());
    }
