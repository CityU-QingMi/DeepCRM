    @Test
    public void testWrite() throws Exception
    {
        ByteArrayEndPoint endp = new ByteArrayEndPoint(_scheduler, 5000, (byte[])null, 15);
        endp.setGrowOutput(false);
        endp.setOutput(BufferUtil.allocate(10));

        ByteBuffer data = BufferUtil.toBuffer("Data.");
        ByteBuffer more = BufferUtil.toBuffer(" Some more.");

        FutureCallback fcb = new FutureCallback();
        endp.write( fcb, data);
        assertTrue(fcb.isDone());
        assertEquals(null, fcb.get());
        assertEquals("Data.", endp.getOutputString());

        fcb = new FutureCallback();
        endp.write(fcb, more);
        assertFalse(fcb.isDone());

        assertEquals("Data. Some", endp.getOutputString());
        assertEquals("Data. Some", endp.takeOutputString());

        assertTrue(fcb.isDone());
        assertEquals(null, fcb.get());
        assertEquals(" more.", endp.getOutputString());
        endp.close();
    }
