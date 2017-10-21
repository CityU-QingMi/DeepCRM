    private static void write(OutputStream output, ByteBuffer content) throws IOException
    {
        int length = content.remaining();
        int offset = 0;
        byte[] buffer;
        if (content.hasArray())
        {
            offset = content.arrayOffset();
            buffer = content.array();
        }
        else
        {
            buffer = new byte[length];
            content.get(buffer);
        }
        output.write(buffer, offset, length);
    }
