    public String parse(ByteBuffer buf)
    {
        byte b;
        while (buf.remaining() > 0)
        {
            b = buf.get();
            if (parseByte(b))
            {
                state = State.START;
                return utf.toString();
            }
        }
        // have not reached end of line (yet)
        return null;
    }
