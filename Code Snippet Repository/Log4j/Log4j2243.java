    @Test
    public void testEncodeText_TextFitCharBuff_BytesDontFitByteBuff() throws Exception {
        final StringBuilderEncoder helper = new StringBuilderEncoder(StandardCharsets.UTF_8, 16, 8 * 1024);
        final StringBuilder text = createText(15);
        final SpyByteBufferDestination destination = new SpyByteBufferDestination(14, 15);
        helper.encode(text, destination);

        assertEquals("drained", 1, destination.drainPoints.size());
        assertEquals("drained[0].from", 0, destination.drainPoints.get(0).position);
        assertEquals("drained[0].to", destination.buffer.capacity(), destination.drainPoints.get(0).limit);
        assertEquals("drained[0].length", destination.buffer.capacity(), destination.drainPoints.get(0).length());
        assertEquals("destination.buf.pos", text.length() - destination.buffer.capacity(),
                destination.buffer.position());

        for (int i = 0; i < destination.buffer.capacity(); i++) {
            assertEquals("char at " + i, (byte) text.charAt(i), destination.drained.get(i));
        }
        for (int i = destination.buffer.capacity(); i < text.length(); i++) {
            final int bufIx = i - destination.buffer.capacity();
            assertEquals("char at " + i, (byte) text.charAt(i), destination.buffer.get(bufIx));
        }
    }
