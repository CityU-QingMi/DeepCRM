    @Test
    public void testShortMaskedUtf8Text() throws Exception
    {
        String expectedText = "Hell\uFF4f W\uFF4Frld";

        byte utf[] = expectedText.getBytes(StandardCharsets.UTF_8);

        ByteBuffer buf = ByteBuffer.allocate(24);
        buf.put((byte)0x81);
        buf.put((byte)(0x80 | utf.length));
        MaskedByteBuffer.putMask(buf);
        MaskedByteBuffer.putPayload(buf,utf);
        buf.flip();

        WebSocketPolicy policy = new WebSocketPolicy(WebSocketBehavior.SERVER);
        Parser parser = new UnitParser(policy);
        IncomingFramesCapture capture = new IncomingFramesCapture();
        parser.setIncomingFramesHandler(capture);
        parser.parse(buf);

        capture.assertNoErrors();
        capture.assertHasFrame(OpCode.TEXT,1);
        WebSocketFrame txt = capture.getFrames().poll();
        Assert.assertThat("TextFrame.data",txt.getPayloadAsUTF8(),is(expectedText));
    }
