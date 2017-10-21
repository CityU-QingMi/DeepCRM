    @Test
    public void testEncodeText_TextDoesntFitCharBuff_BytesDontFitByteBuff() throws Exception {
        final StringBuilderEncoder helper = new StringBuilderEncoder(StandardCharsets.UTF_8, 4, 8 * 1024);
        final StringBuilder text = createText(15);
        final SpyByteBufferDestination destination = new SpyByteBufferDestination(3, 17);
        helper.encode(text, destination);

        assertEquals("drained", 4, destination.drainPoints.size());
        assertEquals("destination.buf.pos", 3, destination.buffer.position());

        for (int i = 0; i < text.length() - 3; i++) {
            assertEquals("char at " + i, (byte) text.charAt(i), destination.drained.get(i));
        }
        for (int i = 0; i < 3; i++) {
            assertEquals("char at " + (12 + i), (byte) text.charAt(12 + i), destination.buffer.get(i));
        }
    }
