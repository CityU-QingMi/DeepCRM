    @Test
    public void testFrameTooLargeDueToPolicy() throws Exception
    {
        WebSocketPolicy policy = new WebSocketPolicy(WebSocketBehavior.SERVER);
        // Artificially small buffer/payload
        policy.setInputBufferSize(1024); // read buffer
        policy.setMaxTextMessageBufferSize(1024); // streaming buffer (not used in this test)
        policy.setMaxTextMessageSize(1024); // actual maximum text message size policy
        byte utf[] = new byte[2048];
        Arrays.fill(utf,(byte)'a');

        Assert.assertThat("Must be a medium length payload",utf.length,allOf(greaterThan(0x7E),lessThan(0xFFFF)));

        ByteBuffer buf = ByteBuffer.allocate(utf.length + 8);
        buf.put((byte)0x81); // text frame, fin = true
        buf.put((byte)(0x80 | 0x7E)); // 0x7E == 126 (a 2 byte payload length)
        buf.putShort((short)utf.length);
        MaskedByteBuffer.putMask(buf);
        MaskedByteBuffer.putPayload(buf,utf);
        buf.flip();

        UnitParser parser = new UnitParser(policy);
        IncomingFramesCapture capture = new IncomingFramesCapture();
        parser.setIncomingFramesHandler(capture);
        parser.parseQuietly(buf);

        capture.assertHasErrors(MessageTooLargeException.class,1);
        capture.assertHasNoFrames();

        MessageTooLargeException err = (MessageTooLargeException)capture.getErrors().poll();
        Assert.assertThat("Error.closeCode",err.getStatusCode(),is(StatusCode.MESSAGE_TOO_LARGE));
    }
