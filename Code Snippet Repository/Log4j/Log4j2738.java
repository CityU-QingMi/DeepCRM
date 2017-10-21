    @Test
    public void testPrint_char() throws Exception {
        for (final char c : FIRST.toCharArray()) {
            this.print.print(c);
            assertMessages();
        }
        this.print.println();
        assertMessages(FIRST);
        assertEquals(FIRST + NEWLINE, this.wrapped.toString());
    }
