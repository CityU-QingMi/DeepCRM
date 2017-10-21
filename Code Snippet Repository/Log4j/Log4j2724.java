    @Test
    public void testWrite_IgnoresWindowsNewline() throws IOException {
        this.writer.write(FIRST + "\r\n");
        this.writer.write(LAST);
        this.writer.close();
        assertMessages(FIRST, LAST);
        if (this.wrapped != null) {
            assertEquals(FIRST + "\r\n" + LAST, this.wrapped.toString());
        }
    }
