    @Test
    public void testRead_IgnoresWindowsNewline() throws IOException {
        final byte[] bytes = new byte[1024];
        final int len = this.in.read(bytes);
        this.read.write(bytes, 0, len);
        assertMessages(FIRST);
        assertEquals(FIRST + "\r\n" + LAST, this.read.toString());
        this.in.close();
        assertMessages(FIRST, LAST);
    }
