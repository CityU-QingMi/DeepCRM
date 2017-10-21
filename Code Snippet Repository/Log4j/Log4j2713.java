    @Test
    public void testClose_HasRemainingData() throws IOException {
        this.out.write(FIRST.getBytes());
        assertMessages();
        this.out.close();
        assertMessages(FIRST);
        if (this.wrapped != null) {
            assertEquals(FIRST, this.wrapped.toString());
        }
    }
