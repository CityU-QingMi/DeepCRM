    @Before
    public void beforeClass() throws Exception
    {
        buffers.set(0);
        pool = new ArrayByteBufferPool()
            {

                @Override
                public ByteBuffer acquire(int size, boolean direct)
                {
                    buffers.incrementAndGet();
                    return super.acquire(size,direct);
                }

                @Override
                public void release(ByteBuffer buffer)
                {
                    buffers.decrementAndGet();
                    super.release(buffer);
                }
            
            };
    }
