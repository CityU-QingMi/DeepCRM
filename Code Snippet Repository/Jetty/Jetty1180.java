    public void testEncode(int n,int i,String expected)
    {
        ByteBuffer buf = BufferUtil.allocate(16);
        int p=BufferUtil.flipToFill(buf);
        if (n<8)
            buf.put((byte)0x00);
        NBitInteger.encode(buf,n,i);
        BufferUtil.flipToFlush(buf,p);
        String r=TypeUtil.toHexString(BufferUtil.toArray(buf));
        assertEquals(expected,r);
        
        assertEquals(expected.length()/2,(n<8?1:0)+NBitInteger.octectsNeeded(n,i));
    }
