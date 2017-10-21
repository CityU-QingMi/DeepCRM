    @Test
    public void testSmallBlock() throws Exception
    {
        String data = "0";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream output = new GZIPOutputStream(baos);
        output.write(data.getBytes(StandardCharsets.UTF_8));
        output.close();
        byte[] bytes = baos.toByteArray();

        GZIPContentDecoder decoder = new GZIPContentDecoder();
        ByteBuffer decoded = decoder.decode(ByteBuffer.wrap(bytes));
        assertEquals(data, StandardCharsets.UTF_8.decode(decoded).toString());
    }
