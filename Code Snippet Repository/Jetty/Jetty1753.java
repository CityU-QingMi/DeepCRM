    @Test
    public void testReleaseAssertion() throws Exception
    {
        int factor = 1024;
        MappedByteBufferPool bufferPool = new MappedByteBufferPool(factor);

        try
        {
            // Release a few small non-pool buffers
            bufferPool.release(ByteBuffer.wrap(StringUtil.getUtf8Bytes("Hello")));

/**/
/**/
/**/
/**/
/**/
/**/
            fail("Expected java.lang.AssertionError, do you have '-ea' JVM command line option enabled?");
        }
        catch (java.lang.AssertionError e)
        {
            // Expected path.
        }
    }
