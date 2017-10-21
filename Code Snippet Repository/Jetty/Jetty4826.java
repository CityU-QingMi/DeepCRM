    public static boolean isPrefix(ByteBuffer prefix, ByteBuffer buffer)
    {
        if (prefix.remaining() > buffer.remaining())
            return false;
        int bi = buffer.position();
        for (int i = prefix.position(); i < prefix.limit(); i++)
            if (prefix.get(i) != buffer.get(bi++))
                return false;
        return true;
    }
