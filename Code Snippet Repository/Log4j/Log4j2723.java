    @Test
    public void testWrite_CharArray_Offset_Length() throws Exception {
        final char[] chars = FIRST.toCharArray();
        final int middle = chars.length / 2;
        final int length = chars.length - middle;
        final String right = new String(chars, middle, length);
        this.writer.write(chars, middle, length);
        assertMessages();
        this.writer.write('\n');
        assertMessages(right);
        if (this.wrapped != null) {
            assertEquals(FIRST.substring(middle, FIRST.length()) + '\n', this.wrapped.toString());
        }
    }
