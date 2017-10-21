    public ByteBuffer getByteBuffer()
    {
        // remember settings
        int limit = buffer.limit();
        int position = buffer.position();

        // flip to flush
        buffer.limit(buffer.position());
        buffer.position(0);

        // get byte buffer
        ByteBuffer bb = StandardCharsets.UTF_8.encode(buffer);

        // restor settings
        buffer.limit(limit);
        buffer.position(position);

        return bb;
    }
