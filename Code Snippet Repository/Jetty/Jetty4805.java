    public static int flipToFill(ByteBuffer buffer)
    {
        int position = buffer.position();
        int limit = buffer.limit();
        if (position == limit)
        {
            buffer.position(0);
            buffer.limit(buffer.capacity());
            return 0;
        }

        int capacity = buffer.capacity();
        if (limit == capacity)
        {
            buffer.compact();
            return 0;
        }

        buffer.position(limit);
        buffer.limit(capacity);
        return position;
    }
