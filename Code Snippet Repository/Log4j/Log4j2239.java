    @Test
    public void testCopyUpToRemainingSpace() throws Exception {
        final CharBuffer buff = CharBuffer.wrap(new char[3]);
        final StringBuilder text = createText(15);
        final int length = TextEncoderHelper.copy(text, 0, buff);
        assertEquals("partial copy", buff.capacity(), length);
        for (int i = 0; i < length; i++) {
            assertEquals("char at " + i, text.charAt(i), buff.get(i));
        }
        assertEquals("no space remaining", 0, buff.remaining());
        assertEquals("position at end", buff.capacity(), buff.position());
    }
