    public static ByteBuffer generate(Frame[] frames)
    {
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
        for (Frame f : frames)
        {
            generator.generateWholeFrame(f,completeBuf);
        }

        BufferUtil.flipToFlush(completeBuf,0);
        if (LOG.isDebugEnabled())
        {
            LOG.debug("generate({} frames) - {}",frames.length,BufferUtil.toDetailString(completeBuf));
        }
        return completeBuf;
    }
