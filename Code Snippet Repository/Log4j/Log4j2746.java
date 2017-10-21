    @Test
    public void testRead_MultipleLines() throws IOException {
        this.wrapped = new StringReader(FIRST + "\n" + LAST + '\n');
        this.reader = createReader();

        final char[] chars = new char[1024];
        final int len = this.reader.read(chars);
        this.read.write(chars, 0, len);
        assertMessages(FIRST, LAST);
        assertEquals(FIRST + '\n' + LAST + '\n', this.read.toString());
    }
