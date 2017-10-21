    public static String toDetailHint(byte[] data, int offset, int len)
    {
        StringBuilder buf = new StringBuilder();
        ByteBuffer buffer = ByteBuffer.wrap(data,offset,len);

        buf.append("byte[").append(data.length);
        buf.append("](o=").append(offset);
        buf.append(",len=").append(len);

        buf.append(")<<<");
        for (int i = buffer.position(); i < buffer.limit(); i++)
        {
            char c = (char)buffer.get(i);
            if ((c >= ' ') && (c <= 127))
            {
                buf.append(c);
            }
            else if ((c == '\r') || (c == '\n'))
            {
                buf.append('|');
            }
            else
            {
                buf.append('\ufffd');
            }
            if ((i == (buffer.position() + 16)) && (buffer.limit() > (buffer.position() + 32)))
            {
                buf.append("...");
                i = buffer.limit() - 16;
            }
        }
        buf.append(">>>");

        return buf.toString();
    }
