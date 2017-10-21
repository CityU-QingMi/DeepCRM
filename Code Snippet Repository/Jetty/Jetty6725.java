    @Test
    public void testSingleUnmasked64KByteBinaryMessage()
    {
        int dataSize = 1024 * 64;

        ByteBuffer buf = ByteBuffer.allocate((dataSize + 10));
        // Raw bytes as found in RFC 6455, Section 5.7 - Examples
        // 64 Kbytes binary message in a single unmasked frame
        buf.put(new byte[]
                { (byte)0x82, 0x7F });
        buf.putLong(dataSize); // 64bit size
        for (int i = 0; i < dataSize; i++)
        {
            buf.put((byte)0x77);
        }
        buf.flip();

        WebSocketPolicy policy = new WebSocketPolicy(WebSocketBehavior.CLIENT);
        Parser parser = new UnitParser(policy);
        IncomingFramesCapture capture = new IncomingFramesCapture();
        parser.setIncomingFramesHandler(capture);
        parser.parse(buf);

        capture.assertNoErrors();
        capture.assertHasFrame(OpCode.BINARY,1);

        Frame bin = capture.getFrames().poll();

        Assert.assertThat("BinaryFrame.payloadLength",bin.getPayloadLength(),is(dataSize));
        ByteBuffer data = bin.getPayload();
        Assert.assertThat("BinaryFrame.payload.length",data.remaining(),is(dataSize));

        for (int i = 0; i < dataSize; i++)
        {
            Assert.assertThat("BinaryFrame.payload[" + i + "]",data.get(i),is((byte)0x77));
        }
    }
