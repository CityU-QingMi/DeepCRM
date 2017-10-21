    public static HttpMethod lookAheadGet(ByteBuffer buffer)
    {
        if (buffer.hasArray())
            return lookAheadGet(buffer.array(),buffer.arrayOffset()+buffer.position(),buffer.arrayOffset()+buffer.limit());
        
        int l = buffer.remaining();
        if (l>=4)
        {
            HttpMethod m = CACHE.getBest(buffer,0,l);
            if (m!=null)
            {
                int ml = m.asString().length();
                if (l>ml && buffer.get(buffer.position()+ml)==' ')
                    return m;
            }
        }
        return null;
    }
