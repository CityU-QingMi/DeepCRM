    @Test
    public void testParse65536ByteBinaryCase1_2_7()
    {
        int length = 65536;

        ByteBuffer expected = ByteBuffer.allocate(length + 11);

        expected.put(new byte[]
                { (byte)0x82 });
        byte b = 0x00; // no masking
        b |= 0x7F;
        expected.put(b);
        expected.put(new byte[]{0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00});

        for ( int i = 0 ; i < length ; ++i )
        {
            expected.put("*".getBytes());
        }

        expected.flip();

        WebSocketPolicy policy = new WebSocketPolicy(WebSocketBehavior.CLIENT);
        policy.setMaxBinaryMessageSize(length);
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
