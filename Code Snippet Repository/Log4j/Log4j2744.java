    @Test
    public void testRead_IgnoresWindowsNewline() throws IOException {
        final char[] chars = new char[1024];
        final int len = this.reader.read(chars);
        this.read.write(chars, 0, len);
        if (!(this.reader instanceof BufferedReader)) {
            assertMessages(FIRST);
        }
        assertEquals(FIRST + "\r\n" + LAST, this.read.toString());
        this.reader.close();
        assertMessages(FIRST, LAST);
    }
