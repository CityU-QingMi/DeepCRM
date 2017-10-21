    public static Input from(final ReadableByteChannel in)
    {
        return new Input()
        {
            @Override
            public int fillBuffer() throws IOException
            {
                BufferUtil.compact(_buffer);
                int pos=BufferUtil.flipToFill(_buffer);
                int len=in.read(_buffer);
                if (len<0)
                    _eof=true;
                BufferUtil.flipToFlush(_buffer,pos);
                return len;
            }
        };
    }
