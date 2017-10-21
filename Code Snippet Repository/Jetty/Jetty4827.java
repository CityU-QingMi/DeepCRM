    public static int put(ByteBuffer from, ByteBuffer to)
    {
        int put;
        int remaining = from.remaining();
        if (remaining > 0)
        {
            if (remaining <= to.remaining())
            {
                to.put(from);
                put = remaining;
                from.position(from.limit());
            }
            else if (from.hasArray())
            {
                put = to.remaining();
                to.put(from.array(), from.arrayOffset() + from.position(), put);
                from.position(from.position() + put);
            }
            else
            {
                put = to.remaining();
                ByteBuffer slice = from.slice();
                slice.limit(put);
                to.put(slice);
                from.position(from.position() + put);
            }
        }
        else
            put = 0;

        return put;
    }
