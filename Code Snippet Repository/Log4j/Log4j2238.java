    @Test
    public void testCopyCopiesAllDataIfSuffientRemainingSpace() throws Exception {
        final CharBuffer buff = CharBuffer.wrap(new char[16]);
        final StringBuilder text = createText(15);
        final int length = TextEncoderHelper.copy(text, 0, buff);
        assertEquals("everything fits", text.length(), length);
        for (int i = 0; i < length; i++) {
            assertEquals("char at " + i, text.charAt(i), buff.get(i));
        }
        assertEquals("position moved by length", text.length(), buff.position());
    }
