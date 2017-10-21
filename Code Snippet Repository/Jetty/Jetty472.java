    @Test
    public void testSmallBlockWithGZIPChunkedAtEnd() throws Exception
    {
        String data = "0";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream output = new GZIPOutputStream(baos);
        output.write(data.getBytes(StandardCharsets.UTF_8));
        output.close();
        byte[] bytes = baos.toByteArray();

        // The trailer is 8 bytes, chunk the last 9 bytes
        byte[] bytes1 = new byte[bytes.length - 9];
        System.arraycopy(bytes, 0, bytes1, 0, bytes1.length);
        byte[] bytes2 = new byte[bytes.length - bytes1.length];
        System.arraycopy(bytes, bytes1.length, bytes2, 0, bytes2.length);

        GZIPContentDecoder decoder = new GZIPContentDecoder();
        ByteBuffer decoded = decoder.decode(ByteBuffer.wrap(bytes1));
        assertEquals(data, StandardCharsets.UTF_8.decode(decoded).toString());
        assertFalse(decoder.isFinished());
        decoded = decoder.decode(ByteBuffer.wrap(bytes2));
        assertEquals(0, decoded.remaining());
        assertTrue(decoder.isFinished());
    }
