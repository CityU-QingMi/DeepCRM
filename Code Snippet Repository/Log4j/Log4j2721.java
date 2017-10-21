    @Test
    public void testWrite_Character() throws Exception {
        for (final char c : FIRST.toCharArray()) {
            this.writer.write(c);
            assertMessages();
        }
        this.writer.write('\n');
        assertMessages(FIRST);
        if (this.wrapped != null) {
            assertEquals(FIRST + '\n', this.wrapped.toString());
        }
    }
