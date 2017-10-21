    @Test
    public void testRead_ByteArray() throws Exception {
        final byte[] bytes = new byte[FIRST.length()];
        assertEquals("len", bytes.length, this.in.read(bytes));
        if (!(this.in instanceof BufferedInputStream)) {
            assertMessages();
        }
        this.in.read(bytes);
        assertMessages(FIRST);
    }
