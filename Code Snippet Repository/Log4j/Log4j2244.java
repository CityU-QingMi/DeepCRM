    @Test
    public void testEncodeText_TextFitCharBuff_BytesDontFitByteBuff_MultiplePasses() throws Exception {
        final StringBuilderEncoder helper = new StringBuilderEncoder(StandardCharsets.UTF_8, 16, 8 * 1024);
        final StringBuilder text = createText(15);
        final SpyByteBufferDestination destination = new SpyByteBufferDestination(4, 20);
        helper.encode(text, destination);

        assertEquals("drained", 3, destination.drainPoints.size());
        assertEquals("drained[0].from", 0, destination.drainPoints.get(0).position);
        assertEquals("drained[0].to", destination.buffer.capacity(), destination.drainPoints.get(0).limit);
        assertEquals("drained[0].length", destination.buffer.capacity(), destination.drainPoints.get(0).length());
        assertEquals("drained[1].from", 0, destination.drainPoints.get(1).position);
        assertEquals("drained[1].to", destination.buffer.capacity(), destination.drainPoints.get(1).limit);
        assertEquals("drained[1].length", destination.buffer.capacity(), destination.drainPoints.get(1).length());
        assertEquals("drained[2].from", 0, destination.drainPoints.get(2).position);
        assertEquals("drained[2].to", destination.buffer.capacity(), destination.drainPoints.get(2).limit);
        assertEquals("drained[2].length", destination.buffer.capacity(), destination.drainPoints.get(2).length());
        assertEquals("destination.buf.pos", text.length() - 3 * destination.buffer.capacity(),
                destination.buffer.position());

        for (int i = 0; i < 3 * destination.buffer.capacity(); i++) {
            assertEquals("char at " + i, (byte) text.charAt(i), destination.drained.get(i));
        }
        for (int i = 3 * destination.buffer.capacity(); i < text.length(); i++) {
            final int bufIx = i - 3 * destination.buffer.capacity();
            assertEquals("char at " + i, (byte) text.charAt(i), destination.buffer.get(bufIx));
        }
    }
