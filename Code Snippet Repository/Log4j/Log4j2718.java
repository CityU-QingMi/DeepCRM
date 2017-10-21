    @Test
    public void testWrite_Int() throws Exception {
        for (final byte b : "int".getBytes()) {
            this.out.write(b);
            assertMessages();
        }
        this.out.write('\n');
        assertMessages("int");
        if (this.wrapped != null) {
            assertEquals("int" + '\n', this.wrapped.toString());
        }
    }
