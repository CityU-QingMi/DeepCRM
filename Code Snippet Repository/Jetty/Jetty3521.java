    @Before
    public void init() throws Exception
    {
        _bytes = BufferUtil.allocate(2048);

        final ByteBufferPool pool = new ArrayByteBufferPool();
        
        HttpChannel channel = new HttpChannel(null,new HttpConfiguration(),null,null)
        {
            @Override
            public ByteBufferPool getByteBufferPool()
            {
                return pool;
            }
        };
        
        _httpOut = new HttpOutput(channel)
        {
            @Override
            public void write(byte[] b, int off, int len) throws IOException
            {
                BufferUtil.append(_bytes, b, off, len);
            }
        };
    }
