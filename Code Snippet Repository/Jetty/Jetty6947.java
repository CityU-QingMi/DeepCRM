    public static ByteBuffer generate(List<WebSocketFrame> frames)
    {
        // Create non-symmetrical mask (helps show mask bytes order issues)
        byte[] MASK =
        { 0x11, 0x22, 0x33, 0x44 };

        // the generator
        Generator generator = new UnitGenerator();

        // Generate into single bytebuffer
        int buflen = 0;
        for (Frame f : frames)
        {
            buflen += f.getPayloadLength() + Generator.MAX_HEADER_LENGTH;
        }
        ByteBuffer completeBuf = ByteBuffer.allocate(buflen);
        BufferUtil.clearToFill(completeBuf);

        // Generate frames
        for (WebSocketFrame f : frames)
        {
            f.setMask(MASK); // make sure we have the test mask set
            BufferUtil.put(generator.generateHeaderBytes(f),completeBuf);
            ByteBuffer window = f.getPayload();
            if (BufferUtil.hasContent(window))
            {
                BufferUtil.put(window,completeBuf);
            }
        }

        BufferUtil.flipToFlush(completeBuf,0);
        if (LOG.isDebugEnabled())
        {
            LOG.debug("generate({} frames) - {}",frames.size(),BufferUtil.toDetailString(completeBuf));
        }
        return completeBuf;
    }
