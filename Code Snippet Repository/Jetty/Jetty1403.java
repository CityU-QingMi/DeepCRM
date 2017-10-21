    @Test
    public void testCachedField()
    {
        PreEncodedHttpField field = new PreEncodedHttpField(HttpHeader.ACCEPT,"something");
        ByteBuffer buf = BufferUtil.allocate(256);
        BufferUtil.clearToFill(buf);
        field.putTo(buf,HttpVersion.HTTP_1_0);
        BufferUtil.flipToFlush(buf,0);
        String s=BufferUtil.toString(buf);
        
        assertEquals("Accept: something\r\n",s);
    }
