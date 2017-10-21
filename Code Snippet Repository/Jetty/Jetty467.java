    @Test
    public void testBigBlockOneByteAtATime() throws Exception
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
        GZIPContentDecoder decoder = new GZIPContentDecoder(64);
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        while (buffer.hasRemaining())
        {
            ByteBuffer decoded = decoder.decode(ByteBuffer.wrap(new byte[]{buffer.get()}));
            if (decoded.hasRemaining())
                result += StandardCharsets.UTF_8.decode(decoded).toString();
        }
        assertEquals(data, result);
        assertTrue(decoder.isFinished());
    }
