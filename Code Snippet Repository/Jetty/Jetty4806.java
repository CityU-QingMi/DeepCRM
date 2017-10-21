    public static void writeTo(ByteBuffer buffer, OutputStream out) throws IOException
    {
        if (buffer.hasArray())
        {
            out.write(buffer.array(),buffer.arrayOffset() + buffer.position(),buffer.remaining());
            // update buffer position, in way similar to non-array version of writeTo
            buffer.position(buffer.position() + buffer.remaining());
        }
        else
        {
            byte[] bytes = new byte[TEMP_BUFFER_SIZE];
            while(buffer.hasRemaining()){
                int byteCountToWrite = Math.min(buffer.remaining(), TEMP_BUFFER_SIZE);
                buffer.get(bytes, 0, byteCountToWrite);
                out.write(bytes,0 , byteCountToWrite);
            }
        }
    }
