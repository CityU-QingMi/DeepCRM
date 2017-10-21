    @Test
    public void testParserAndGenerator() throws Exception
    {
        WebSocketPolicy policy = WebSocketPolicy.newClientPolicy();
        Generator gen = new Generator(policy,bufferPool);
        Parser parser = new Parser(policy,bufferPool);
        IncomingFramesCapture capture = new IncomingFramesCapture();
        parser.setIncomingFramesHandler(capture);

        String message = "0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF";

        ByteBuffer out = bufferPool.acquire(8192,false);
        try
        {
            // Generate Buffer
            BufferUtil.flipToFill(out);
            WebSocketFrame frame = new TextFrame().setPayload(message);
            ByteBuffer header = gen.generateHeaderBytes(frame);
            ByteBuffer payload = frame.getPayload();
            out.put(header);
            out.put(payload);

            // Parse Buffer
            BufferUtil.flipToFlush(out,0);
            parser.parse(out);
        }
        finally
        {
            bufferPool.release(out);
        }

        // Validate
        capture.assertNoErrors();
        capture.assertHasFrame(OpCode.TEXT,1);

        TextFrame txt = (TextFrame)capture.getFrames().poll();
        Assert.assertThat("Text parsed",txt.getPayloadAsUTF8(),is(message));
    }
