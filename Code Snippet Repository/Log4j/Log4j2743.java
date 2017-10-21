    @Test
    public void testRead_CharArray_Offset_Length() throws Exception {
        final char[] chars = new char[1024];
        assertEquals("len", FIRST.length(), this.reader.read(chars, 0, FIRST.length()));
        if (!(this.reader instanceof BufferedReader)) {
            assertMessages();
        }
        this.reader.read(chars);
        this.reader.close();
        assertMessages(FIRST, LAST);
    }
