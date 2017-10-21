    @Test
    public void testWrite_IgnoresWindowsNewline() throws IOException {
        this.out.write(FIRST.getBytes());
        this.out.write("\r\n".getBytes());
        this.out.write(LAST.getBytes());
        this.out.close();
        assertMessages(FIRST, LAST);
        if (this.wrapped != null) {
            assertEquals(FIRST + "\r\n" + LAST, this.wrapped.toString());
        }
    }
