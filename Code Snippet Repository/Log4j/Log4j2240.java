    @Test
    public void testCopyDoesNotWriteBeyondStringText() throws Exception {
        final CharBuffer buff = CharBuffer.wrap(new char[5]);
        assertEquals("initial buffer position", 0, buff.position());
        final StringBuilder text = createText(2);
        final int length = TextEncoderHelper.copy(text, 0, buff);
        assertEquals("full copy", text.length(), length);
        for (int i = 0; i < length; i++) {
            assertEquals("char at " + i, text.charAt(i), buff.get(i));
        }
        assertEquals("resulting buffer position", text.length(), buff.position());
        for (int i = length; i < buff.capacity(); i++) {
            assertEquals("unset char at " + i, 0, buff.get(i));
        }
    }
