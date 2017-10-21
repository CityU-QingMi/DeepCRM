    public static ByteBuffer toDirectBuffer(String s, Charset charset)
    {
        if (s == null)
            return EMPTY_BUFFER;
        byte[] bytes = s.getBytes(charset);
        ByteBuffer buf = ByteBuffer.allocateDirect(bytes.length);
        buf.put(bytes);
        buf.flip();
        return buf;
    }
