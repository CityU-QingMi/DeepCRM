    @Test
    public void testBigBlockWithExtraBytes() throws Exception
    {
        String data1 = "0123456789ABCDEF";
        for (int i = 0; i < 10; ++i)
            data1 += data1;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream output = new GZIPOutputStream(baos);
        output.write(data1.getBytes(StandardCharsets.UTF_8));
        output.close();
        byte[] bytes1 = baos.toByteArray();

        String data2 = "HELLO";
        byte[] bytes2 = data2.getBytes(StandardCharsets.UTF_8);

        byte[] bytes = new byte[bytes1.length + bytes2.length];
        System.arraycopy(bytes1, 0, bytes, 0, bytes1.length);
        System.arraycopy(bytes2, 0, bytes, bytes1.length, bytes2.length);

        String result = "";
        GZIPContentDecoder decoder = new GZIPContentDecoder(64);
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        while (buffer.hasRemaining())
        {
            ByteBuffer decoded = decoder.decode(buffer);
            if (decoded.hasRemaining())
                result += StandardCharsets.UTF_8.decode(decoded).toString();
            decoder.release(decoded);
            if (decoder.isFinished())
                break;
        }
        assertEquals(data1, result);
        assertTrue(buffer.hasRemaining());
        assertEquals(data2, StandardCharsets.UTF_8.decode(buffer).toString());
    }
