    public static ByteBuffer ensureCapacity(ByteBuffer buffer, int capacity)
    {
        if (buffer==null)
            return allocate(capacity);
        
        if (buffer.capacity()>=capacity)
            return buffer;
        
        if (buffer.hasArray())
            return ByteBuffer.wrap(Arrays.copyOfRange(buffer.array(), buffer.arrayOffset(), buffer.arrayOffset()+capacity),buffer.position(),buffer.remaining());
        
        throw new UnsupportedOperationException();
    }
