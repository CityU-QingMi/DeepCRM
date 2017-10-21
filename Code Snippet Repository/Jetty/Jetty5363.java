    @Test
    public void testToInt() throws Exception
    {
        ByteBuffer buf[] =
        {
            BufferUtil.toBuffer("0"),
            BufferUtil.toBuffer(" 42 "),
            BufferUtil.toBuffer("   43abc"),
            BufferUtil.toBuffer("-44"),
            BufferUtil.toBuffer(" - 45;"),
            BufferUtil.toBuffer("-2147483648"),
            BufferUtil.toBuffer("2147483647"),
        };

        int val[] =
        {
            0,42,43,-44,-45,-2147483648,2147483647
        };

        for (int i=0;i<buf.length;i++)
            assertEquals("t"+i, val[i], BufferUtil.toInt(buf[i]));
    }
