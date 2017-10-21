    @Test
    public void testParse125OctetPingCase2_4()
    {
        byte[] bytes = new byte[125];

        for ( int i = 0 ; i < bytes.length ; ++i )
        {
            bytes[i] = Integer.valueOf(Integer.toOctalString(i)).byteValue();
        }

        ByteBuffer expected = ByteBuffer.allocate(bytes.length + 32);

        expected.put(new byte[]
                { (byte)0x89 });

        byte b = 0x00; // no masking
        b |= bytes.length & 0x7F;
        expected.put(b);
        expected.put(bytes);

        expected.flip();

        Parser parser = new UnitParser(policy);
        IncomingFramesCapture capture = new IncomingFramesCapture();
        parser.setIncomingFramesHandler(capture);
        parser.parse(expected);

        capture.assertNoErrors();
        capture.assertHasFrame(OpCode.PING,1);

        Frame pActual = capture.getFrames().poll();
        Assert.assertThat("PingFrame.payloadLength",pActual.getPayloadLength(),is(bytes.length));
        Assert.assertEquals("PingFrame.payload",bytes.length,pActual.getPayloadLength());
    }
