    @Test
    public void testBigBlock() throws Exception
    {
        String data = "0123456789ABCDEF";
        for (int i = 0; i < 10; ++i)
            data += data;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream output = new GZIPOutputStream(baos);
        output.write(data.getBytes(StandardCharsets.UTF_8));
        output.close();
        byte[] bytes = baos.toByteArray();

        String result = "";
        GZIPContentDecoder decoder = new GZIPContentDecoder();
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        while (buffer.hasRemaining())
        {
            ByteBuffer decoded = decoder.decode(buffer);
            result += StandardCharsets.UTF_8.decode(decoded).toString();
        }
        assertEquals(data, result);
    }
