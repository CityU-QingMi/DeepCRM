    @Test
    public void testRead_int() throws Exception {
        for (int i = 0; i < FIRST.length(); i++) {
            this.read.write(this.in.read());
        }
        if (!(this.in instanceof BufferedInputStream)) {
            assertMessages();
        }
        assertEquals("carriage return", '\r', this.in.read());
        if (!(this.in instanceof BufferedInputStream)) {
            assertMessages();
        }
        assertEquals("newline", '\n', this.in.read());
        assertMessages(FIRST);
    }
