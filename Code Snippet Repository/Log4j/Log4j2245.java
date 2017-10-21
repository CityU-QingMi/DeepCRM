    @Test
    public void testEncodeText_TextDoesntFitCharBuff_BytesFitByteBuff() throws Exception {
        final StringBuilderEncoder helper = new StringBuilderEncoder(StandardCharsets.UTF_8, 4, 8 * 1024);
        final StringBuilder text = createText(15);
        final SpyByteBufferDestination destination = new SpyByteBufferDestination(17, 17);
        helper.encode(text, destination);

        assertEquals("drained", 0, destination.drainPoints.size());
        assertEquals("destination.buf.pos", text.length(), destination.buffer.position());

        for (int i = 0; i < text.length(); i++) {
            assertEquals("char at " + i, (byte) text.charAt(i), destination.buffer.get(i));
        }
    }
