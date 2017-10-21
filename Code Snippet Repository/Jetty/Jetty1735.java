    @Test
    public void testGrowingFlush() throws Exception
    {
        ByteArrayEndPoint endp = new ByteArrayEndPoint((byte[])null,15);
        endp.setGrowOutput(true);

        assertEquals(true,endp.flush(BufferUtil.toBuffer("some output")));
        assertEquals("some output",endp.getOutputString());

        assertEquals(true,endp.flush(BufferUtil.toBuffer(" some more")));
        assertEquals("some output some more",endp.getOutputString());

        assertEquals(true,endp.flush());
        assertEquals("some output some more",endp.getOutputString());

        assertEquals(true,endp.flush(BufferUtil.EMPTY_BUFFER));
        assertEquals("some output some more",endp.getOutputString());

        assertEquals(true,endp.flush(BufferUtil.EMPTY_BUFFER,BufferUtil.toBuffer(" and"),BufferUtil.toBuffer(" more")));
        assertEquals("some output some more and more",endp.getOutputString());
        endp.close();
    }
