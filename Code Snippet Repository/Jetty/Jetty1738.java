    @Test
    public void testClientClose() throws Exception
    {
        EndPointPair<T> c = newConnection();
        ByteBuffer buffer = BufferUtil.allocate(4096);

        c.client.flush(BufferUtil.toBuffer("request"));
        int len = c.server.fill(buffer);
        assertEquals(7,len);
        assertEquals("request",BufferUtil.toString(buffer));

        assertTrue(c.client.isOpen());
        assertFalse(c.client.isOutputShutdown());
        assertTrue(c.server.isOpen());
        assertFalse(c.server.isOutputShutdown());

        c.client.close();

        assertFalse(c.client.isOpen());
        assertTrue(c.client.isOutputShutdown());
        assertTrue(c.server.isOpen());
        assertFalse(c.server.isOutputShutdown());

        len = c.server.fill(buffer);
        assertEquals(-1,len);

        assertFalse(c.client.isOpen());
        assertTrue(c.client.isOutputShutdown());
        assertTrue(c.server.isOpen());
        assertFalse(c.server.isOutputShutdown());

        c.server.shutdownOutput();

        assertFalse(c.client.isOpen());
        assertTrue(c.client.isOutputShutdown());
        assertFalse(c.server.isOpen());
        assertTrue(c.server.isOutputShutdown());
    }
