    @Test
    public void testRead_int() throws Exception {
        for (int i = 0; i < FIRST.length(); i++) {
            this.read.write(this.reader.read());
        }
        if (!(this.reader instanceof BufferedReader)) {
            assertMessages();
        }
        assertEquals("carriage return", '\r', this.reader.read());
        if (!(this.reader instanceof BufferedReader)) {
            assertMessages();
        }
        assertEquals("newline", '\n', this.reader.read());
        assertMessages(FIRST);
    }
