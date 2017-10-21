    private ByteBuffer asByteBuffer(int statusCode, String reason)
    {
        int len = 2; // status code length
        byte utf[] = null;
        if (StringUtil.isNotBlank(reason))
        {
            utf = StringUtil.getUtf8Bytes(reason);
            len += utf.length;
        }

        ByteBuffer buf = BufferUtil.allocate(len);
        BufferUtil.flipToFill(buf);
        buf.put((byte)((statusCode >>> 8) & 0xFF));
        buf.put((byte)((statusCode >>> 0) & 0xFF));

        if (utf != null)
        {
            buf.put(utf,0,utf.length);
        }
        BufferUtil.flipToFlush(buf,0);
        
        return buf;
    }
