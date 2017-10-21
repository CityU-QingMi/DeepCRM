    @Test
    public void testClose_HasRemainingData() throws IOException {
        final char[] chars = new char[1024];
        this.reader.read(chars);
        if (!(this.reader instanceof BufferedReader)) {
            assertMessages(FIRST);
        }
        this.reader.close();
        assertMessages(FIRST, LAST);
    }
