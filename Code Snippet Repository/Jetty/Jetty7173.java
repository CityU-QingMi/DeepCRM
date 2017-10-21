    @OnWebSocketFrame
    public void onFrame(Session session, Frame frame)
    {
        if (!frame.getType().isData())
        {
            // Don't process non-data frames
            return;
        }

        ByteBuffer data = frame.getPayload();

        int half = data.remaining() / 2;

        ByteBuffer buf1 = data.slice();
        ByteBuffer buf2 = data.slice();

        buf1.limit(half);
        buf2.position(half);

        RemoteEndpoint remote = session.getRemote();
        try
        {
            switch (frame.getType())
            {
                case BINARY:
                    remote.sendBytes(buf1,null);
                    remote.sendBytes(buf2,null);
                    break;
                case TEXT:
                    // NOTE: This impl is not smart enough to split on a UTF8 boundary
                    remote.sendString(BufferUtil.toUTF8String(buf1),null);
                    remote.sendString(BufferUtil.toUTF8String(buf2),null);
                    break;
                default:
                    throw new IOException("Unexpected frame type: " + frame.getType());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace(System.err);
        }
    }
