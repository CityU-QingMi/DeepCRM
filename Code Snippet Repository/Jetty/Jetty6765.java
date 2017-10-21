    @Test
    public void testParse125ByteBinaryCase1_2_2()
    {
        int length = 125;

        ByteBuffer expected = ByteBuffer.allocate(length + 5);

        expected.put(new byte[]
                { (byte)0x82 });
        byte b = 0x00; // no masking
        b |= length & 0x7F;
        expected.put(b);

        for ( int i = 0 ; i < length ; ++i )
        {
            expected.put("*".getBytes());
        }

        expected.flip();

        Parser parser = new UnitParser(policy);
        IncomingFramesCapture capture = new IncomingFramesCapture();
        parser.setIncomingFramesHandler(capture);
        parser.parse(expected);

        capture.assertNoErrors();
        capture.assertHasFrame(OpCode.BINARY,1);

        Frame pActual = capture.getFrames().poll();
        Assert.assertThat("BinaryFrame.payloadLength",pActual.getPayloadLength(),is(length));
        // Assert.assertEquals("BinaryFrame.payload",length,pActual.getPayloadData().length);
    }
