    @Test
    public void testRead_CharArray() throws Exception {
        final char[] chars = new char[FIRST.length()];
        assertEquals("len", FIRST.length(), this.reader.read(chars));
        if (!(this.reader instanceof BufferedReader)) {
            assertMessages();
        }
        this.reader.read(chars);
        assertMessages(FIRST);
    }
