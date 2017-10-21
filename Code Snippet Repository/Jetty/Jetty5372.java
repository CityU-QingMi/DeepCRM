    @Test
    public void testAppend() throws Exception
    {
        ByteBuffer to = BufferUtil.allocate(8);
        ByteBuffer from=BufferUtil.toBuffer("12345");

        BufferUtil.append(to,from.array(),0,3);
        assertEquals("123",BufferUtil.toString(to));
        BufferUtil.append(to,from.array(),3,2);
        assertEquals("12345",BufferUtil.toString(to));

        try
        {
            BufferUtil.append(to,from.array(),0,5);
            Assert.fail();
        }
        catch(BufferOverflowException e)
        {}
    }
