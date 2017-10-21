    @Test
    public void testTagged()
    {
        MappedByteBufferPool pool = new MappedByteBufferPool.Tagged();

        ByteBuffer buffer = pool.acquire(1024,false);

        assertThat(BufferUtil.toDetailString(buffer),containsString("@T00000001"));
        buffer = pool.acquire(1024,false);
        assertThat(BufferUtil.toDetailString(buffer),containsString("@T00000002"));
    }
