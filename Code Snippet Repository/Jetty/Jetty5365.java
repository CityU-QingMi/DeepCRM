    @Test
    public void testEnsureCapacity() throws Exception
    {
        ByteBuffer b = BufferUtil.toBuffer("Goodbye Cruel World");
        assertTrue(b==BufferUtil.ensureCapacity(b, 0));
        assertTrue(b==BufferUtil.ensureCapacity(b, 10));
        assertTrue(b==BufferUtil.ensureCapacity(b, b.capacity()));


        ByteBuffer b1 = BufferUtil.ensureCapacity(b, 64);
        assertTrue(b!=b1);
        assertEquals(64, b1.capacity());
        assertEquals("Goodbye Cruel World", BufferUtil.toString(b1));

        b1.position(8);
        b1.limit(13);
        assertEquals("Cruel", BufferUtil.toString(b1));
        ByteBuffer b2 = b1.slice();
        assertEquals("Cruel", BufferUtil.toString(b2));
        System.err.println(BufferUtil.toDetailString(b2));
        assertEquals(8, b2.arrayOffset());
        assertEquals(5, b2.capacity());

        assertTrue(b2==BufferUtil.ensureCapacity(b2, 5));

        ByteBuffer b3 = BufferUtil.ensureCapacity(b2, 64);
        assertTrue(b2!=b3);
        assertEquals(64, b3.capacity());
        assertEquals("Cruel", BufferUtil.toString(b3));
        assertEquals(0, b3.arrayOffset());

    }
