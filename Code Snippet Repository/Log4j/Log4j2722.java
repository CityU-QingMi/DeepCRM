    @Test
    public void testWrite_CharArray() throws Exception {
        final char[] chars = FIRST.toCharArray();
        this.writer.write(chars);
        assertMessages();
        this.writer.write('\n');
        assertMessages(FIRST);
        if (this.wrapped != null) {
            assertEquals(FIRST + '\n', this.wrapped.toString());
        }
    }
