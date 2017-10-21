    @Test
    public void testTwoSmallBlocks() throws Exception
    {
        String data1 = "0";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream output = new GZIPOutputStream(baos);
        output.write(data1.getBytes(StandardCharsets.UTF_8));
        output.close();
        byte[] bytes1 = baos.toByteArray();

        String data2 = "1";
        baos = new ByteArrayOutputStream();
        output = new GZIPOutputStream(baos);
        output.write(data2.getBytes(StandardCharsets.UTF_8));
        output.close();
        byte[] bytes2 = baos.toByteArray();

        byte[] bytes = new byte[bytes1.length + bytes2.length];
        System.arraycopy(bytes1, 0, bytes, 0, bytes1.length);
        System.arraycopy(bytes2, 0, bytes, bytes1.length, bytes2.length);

        GZIPContentDecoder decoder = new GZIPContentDecoder();
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        ByteBuffer decoded = decoder.decode(buffer);
        assertEquals(data1, StandardCharsets.UTF_8.decode(decoded).toString());
        assertTrue(decoder.isFinished());
        assertTrue(buffer.hasRemaining());
        decoded = decoder.decode(buffer);
        assertEquals(data2, StandardCharsets.UTF_8.decode(decoded).toString());
        assertTrue(decoder.isFinished());
        assertFalse(buffer.hasRemaining());
    }
