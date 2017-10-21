    public static Input from(final InputStream in)
    {
        return new Input()
        {
            @Override
            public int fillBuffer() throws IOException
            {
                BufferUtil.compact(_buffer);
                int len=in.read(_buffer.array(),_buffer.arrayOffset()+_buffer.limit(),BufferUtil.space(_buffer));
                if (len<0)
                    _eof=true;
                else
                    _buffer.limit(_buffer.limit()+len);
                return len;
            }
        };
    }
