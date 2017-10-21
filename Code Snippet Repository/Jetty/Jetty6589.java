    public void transferTo(ByteBuffer buffer)
    {
        if (buffer.remaining() < length)
        {
            throw new IllegalArgumentException(String.format("Not enough space in ByteBuffer remaining [%d] for accumulated buffers length [%d]",
                    buffer.remaining(),length));
        }

        int position = buffer.position();
        for (byte[] chunk : chunks)
        {
            buffer.put(chunk,0,chunk.length);
        }
        BufferUtil.flipToFlush(buffer,position);
    }
