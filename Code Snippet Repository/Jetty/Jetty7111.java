    protected void fragmentText(List<WebSocketFrame> frames, byte msg[])
    {
        int len = msg.length;
        boolean continuation = false;
        byte mini[];
        for (int i = 0; i < len; i++)
        {
            DataFrame frame = null;
            if (continuation)
            {
                frame = new ContinuationFrame();
            }
            else
            {
                frame = new TextFrame();
            }
            mini = new byte[1];
            mini[0] = msg[i];
            frame.setPayload(ByteBuffer.wrap(mini));
            boolean isLast = (i >= (len - 1));
            frame.setFin(isLast);
            frames.add(frame);
            continuation = true;
        }
    }
