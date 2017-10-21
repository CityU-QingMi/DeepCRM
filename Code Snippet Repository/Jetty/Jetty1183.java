    @Test
    public void testEncodeExampleD_1_2()
    {
        ByteBuffer buf = BufferUtil.allocate(16);
        int p=BufferUtil.flipToFill(buf);
        buf.put((byte)0x88);
        buf.put((byte)0x00);
        NBitInteger.encode(buf,5,1337);
        BufferUtil.flipToFlush(buf,p);
        
        String r=TypeUtil.toHexString(BufferUtil.toArray(buf));
        
        Assert.assertEquals("881f9a0a",r);
        
    }
