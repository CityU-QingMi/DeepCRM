    @Test
    public void testPutDirect() throws Exception
    {
        ByteBuffer to = BufferUtil.allocateDirect(10);
        ByteBuffer from=BufferUtil.toBuffer("12345");

        BufferUtil.clear(to);
        assertEquals(5,BufferUtil.append(to,from));
        assertTrue(BufferUtil.isEmpty(from));
        assertEquals("12345",BufferUtil.toString(to));

        from=BufferUtil.toBuffer("XX67890ZZ");
        from.position(2);

        assertEquals(5,BufferUtil.append(to,from));
        assertEquals(2,from.remaining());
        assertEquals("1234567890",BufferUtil.toString(to));
    }
