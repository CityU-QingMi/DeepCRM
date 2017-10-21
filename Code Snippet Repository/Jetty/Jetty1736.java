    @Test
    public void testFlush() throws Exception
    {
        ByteArrayEndPoint endp = new ByteArrayEndPoint((byte[])null,15);
        endp.setGrowOutput(false);
        endp.setOutput(BufferUtil.allocate(10));

        ByteBuffer data = BufferUtil.toBuffer("Some more data.");
        assertEquals(false,endp.flush(data));
        assertEquals("Some more ",endp.getOutputString());
        assertEquals("data.",BufferUtil.toString(data));

        assertEquals("Some more ",endp.takeOutputString());

        assertEquals(true,endp.flush(data));
        assertEquals("data.",BufferUtil.toString(endp.takeOutput()));
        endp.close();
    }
