    @Test
    public void testFill() throws Exception
    {
        ByteArrayEndPoint endp = new ByteArrayEndPoint();
        endp.addInput("test input");

        ByteBuffer buffer = BufferUtil.allocate(1024);

        assertEquals(10,endp.fill(buffer));
        assertEquals("test input",BufferUtil.toString(buffer));

        assertEquals(0,endp.fill(buffer));

        endp.addInput(" more");
        assertEquals(5,endp.fill(buffer));
        assertEquals("test input more",BufferUtil.toString(buffer));

        assertEquals(0,endp.fill(buffer));

        endp.addInput((ByteBuffer)null);

        assertEquals(-1,endp.fill(buffer));

        endp.close();

        try
        {
            endp.fill(buffer);
            fail();
        }
        catch(IOException e)
        {
            assertThat(e.getMessage(),containsString("CLOSED"));
        }

        endp.reset();
        endp.addInput("and more");
        buffer = BufferUtil.allocate(4);

        assertEquals(4,endp.fill(buffer));
        assertEquals("and ",BufferUtil.toString(buffer));
        assertEquals(0,endp.fill(buffer));
        BufferUtil.clear(buffer);
        assertEquals(4,endp.fill(buffer));
        assertEquals("more",BufferUtil.toString(buffer));
    }
