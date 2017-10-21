    private static void appendDebugString(StringBuilder buf,ByteBuffer buffer)
    {
        try
        {
            for (int i = 0; i < buffer.position(); i++)
            {
                appendContentChar(buf,buffer.get(i));
                if (i == 16 && buffer.position() > 32)
                {
                    buf.append("...");
                    i = buffer.position() - 16;
                }
            }
            buf.append("<<<");
            for (int i = buffer.position(); i < buffer.limit(); i++)
            {
                appendContentChar(buf,buffer.get(i));
                if (i == buffer.position() + 16 && buffer.limit() > buffer.position() + 32)
                {
                    buf.append("...");
                    i = buffer.limit() - 16;
                }
            }
            buf.append(">>>");
            int limit = buffer.limit();
            buffer.limit(buffer.capacity());
            for (int i = limit; i < buffer.capacity(); i++)
            {
                appendContentChar(buf,buffer.get(i));
                if (i == limit + 16 && buffer.capacity() > limit + 32)
                {
                    buf.append("...");
                    i = buffer.capacity() - 16;
                }
            }
            buffer.limit(limit);
        }
        catch(Throwable x)
        {
            Log.getRootLogger().ignore(x);
            buf.append("!!concurrent mod!!");
        }
    }
