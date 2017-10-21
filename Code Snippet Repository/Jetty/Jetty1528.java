    public static Input from(final ByteBuffer data)
    {
        return new Input(data.slice())
        {
            @Override
            public int fillBuffer() throws IOException
            {
                _eof=true;
                return -1;
            }
        };
    }
