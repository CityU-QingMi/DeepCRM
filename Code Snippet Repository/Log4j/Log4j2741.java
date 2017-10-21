    @Test
    public void testClose_NoRemainingData() throws IOException {
        this.wrapped = new StringReader(FIRST + '\n');
        this.reader = createReader();

        final char[] chars = new char[1024];
        this.reader.read(chars);
        assertMessages(FIRST);
        this.reader.close();
        assertMessages(FIRST);
    }
