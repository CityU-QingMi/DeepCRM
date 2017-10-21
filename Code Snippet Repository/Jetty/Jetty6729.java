    @Test
    public void testLongMaskedText() throws Exception
    {
        StringBuffer sb = new StringBuffer(); ;
        for (int i = 0; i < 3500; i++)
        {
            sb.append("Hell\uFF4f Big W\uFF4Frld ");
        }
        sb.append(". The end.");

        String expectedText = sb.toString();
        byte utf[] = expectedText.getBytes(StandardCharsets.UTF_8);

        Assert.assertThat("Must be a long length payload",utf.length,greaterThan(0xFFFF));

        ByteBuffer buf = ByteBuffer.allocate(utf.length + 32);
        buf.put((byte)0x81); // text frame, fin = true
        buf.put((byte)(0x80 | 0x7F)); // 0x7F == 127 (a 8 byte payload length)
        buf.putLong(utf.length);
        MaskedByteBuffer.putMask(buf);
        MaskedByteBuffer.putPayload(buf,utf);
        buf.flip();

        WebSocketPolicy policy = WebSocketPolicy.newServerPolicy();
        policy.setMaxTextMessageSize(100000);
        Parser parser = new UnitParser(policy);
        IncomingFramesCapture capture = new IncomingFramesCapture();
        parser.setIncomingFramesHandler(capture);
        parser.parse(buf);

        capture.assertNoErrors();
        capture.assertHasFrame(OpCode.TEXT,1);
        WebSocketFrame txt = capture.getFrames().poll();
        Assert.assertThat("TextFrame.data",txt.getPayloadAsUTF8(),is(expectedText));
    }
