    @Test
    public void testRead_ByteArray_Offset_Length() throws Exception {
        final byte[] bytes = new byte[FIRST.length() * 2];
        assertEquals("len", FIRST.length(), this.in.read(bytes, 0, FIRST.length()));
        if (!(this.in instanceof BufferedInputStream)) {
            assertMessages();
        }
        this.in.read(bytes);
        assertMessages(FIRST);
    }
