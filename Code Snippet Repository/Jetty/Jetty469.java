    @Test
    public void testNoBlocks() throws Exception
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream output = new GZIPOutputStream(baos);
        output.close();
        byte[] bytes = baos.toByteArray();

        GZIPContentDecoder decoder = new GZIPContentDecoder();
        ByteBuffer decoded = decoder.decode(ByteBuffer.wrap(bytes));
        assertEquals(0, decoded.remaining());
    }
