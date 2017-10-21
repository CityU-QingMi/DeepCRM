    @Test
    public void testMappedFile() throws Exception
    {
        String data="Now is the time for all good men to come to the aid of the party";
        File file = File.createTempFile("test",".txt");
        file.deleteOnExit();
        try(FileWriter out = new FileWriter(file))
        {
            out.write(data);
        }

        ByteBuffer mapped = BufferUtil.toMappedBuffer(file);
        assertEquals(data,BufferUtil.toString(mapped));
        assertTrue(BufferUtil.isMappedBuffer(mapped));

        ByteBuffer direct = BufferUtil.allocateDirect(data.length());
        BufferUtil.clearToFill(direct);
        direct.put(data.getBytes(StandardCharsets.ISO_8859_1));
        BufferUtil.flipToFlush(direct, 0);
        assertEquals(data,BufferUtil.toString(direct));
        assertFalse(BufferUtil.isMappedBuffer(direct));

        ByteBuffer slice = direct.slice();
        assertEquals(data,BufferUtil.toString(slice));
        assertFalse(BufferUtil.isMappedBuffer(slice));

        ByteBuffer duplicate = direct.duplicate();
        assertEquals(data,BufferUtil.toString(duplicate));
        assertFalse(BufferUtil.isMappedBuffer(duplicate));

        ByteBuffer readonly = direct.asReadOnlyBuffer();
        assertEquals(data,BufferUtil.toString(readonly));
        assertFalse(BufferUtil.isMappedBuffer(readonly));
    }
