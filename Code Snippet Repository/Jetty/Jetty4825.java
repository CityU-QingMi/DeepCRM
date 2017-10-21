    public static String toHexSummary(ByteBuffer buffer)
    {
        if (buffer == null)
            return "null";
        StringBuilder buf = new StringBuilder();
        
        buf.append("b[").append(buffer.remaining()).append("]=");
        for (int i = buffer.position(); i < buffer.limit(); i++)
        {
            TypeUtil.toHex(buffer.get(i),buf);
            if (i == buffer.position() + 24 && buffer.limit() > buffer.position() + 32)
            {
                buf.append("...");
                i = buffer.limit() - 8;
            }
        }
        return buf.toString();
    }
