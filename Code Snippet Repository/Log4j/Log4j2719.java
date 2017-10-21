    @Test
    public void testClose_HasRemainingData() throws IOException {
        this.writer.write(FIRST);
        assertMessages();
        this.writer.close();
        assertMessages(FIRST);
        if (this.wrapped != null) {
            assertEquals(FIRST, this.wrapped.toString());
        }
    }
