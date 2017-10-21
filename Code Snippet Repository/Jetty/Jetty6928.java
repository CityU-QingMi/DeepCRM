    public ByteBuffer asNetworkBuffer(List<WebSocketFrame> send)
    {
        int buflen = 0;
        for (Frame f : send)
        {
            buflen += f.getPayloadLength() + Generator.MAX_HEADER_LENGTH;
        }
        ByteBuffer buf = ByteBuffer.allocate(buflen);

        // Generate frames
        for (WebSocketFrame f : send)
        {
            setClientMask(f);
            generator.generateWholeFrame(f,buf);
        }
        buf.flip();
        return buf;
    }
