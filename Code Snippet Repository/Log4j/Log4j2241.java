    @Test
    public void testCopyStartsAtBufferPosition() throws Exception {
        final CharBuffer buff = CharBuffer.wrap(new char[10]);
        final int START_POSITION = 5;
        buff.position(START_POSITION); // set start position
        final StringBuilder text = createText(15);
        final int length = TextEncoderHelper.copy(text, 0, buff);
        assertEquals("partial copy", buff.capacity() - START_POSITION, length);
        for (int i = 0; i < length; i++) {
            assertEquals("char at " + i, text.charAt(i), buff.get(START_POSITION + i));
        }
        assertEquals("buffer position at end", buff.capacity(), buff.position());
    }
