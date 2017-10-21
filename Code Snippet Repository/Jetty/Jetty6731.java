    @Test
    public void testShortMaskedFragmentedText() throws Exception
    {
        String part1 = "Hello ";
        String part2 = "World";

        byte b1[] = part1.getBytes(StandardCharsets.UTF_8);
        byte b2[] = part2.getBytes(StandardCharsets.UTF_8);

        ByteBuffer buf = ByteBuffer.allocate(32);

        // part 1
        buf.put((byte)0x01); // no fin + text
        buf.put((byte)(0x80 | b1.length));
        MaskedByteBuffer.putMask(buf);
        MaskedByteBuffer.putPayload(buf,b1);

        // part 2
        buf.put((byte)0x80); // fin + continuation
        buf.put((byte)(0x80 | b2.length));
        MaskedByteBuffer.putMask(buf);
        MaskedByteBuffer.putPayload(buf,b2);

        buf.flip();

        WebSocketPolicy policy = new WebSocketPolicy(WebSocketBehavior.SERVER);
        Parser parser = new UnitParser(policy);
        IncomingFramesCapture capture = new IncomingFramesCapture();
        parser.setIncomingFramesHandler(capture);
        parser.parse(buf);

        capture.assertNoErrors();
        capture.assertHasFrame(OpCode.TEXT,1);
        capture.assertHasFrame(OpCode.CONTINUATION,1);
        WebSocketFrame txt = capture.getFrames().poll();
        Assert.assertThat("TextFrame[0].data",txt.getPayloadAsUTF8(),is(part1));
        txt = capture.getFrames().poll();
        Assert.assertThat("TextFrame[1].data",txt.getPayloadAsUTF8(),is(part2));
    }
