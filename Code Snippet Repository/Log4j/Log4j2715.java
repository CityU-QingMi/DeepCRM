    @Test
    public void testWrite_ByteArray() throws Exception {
        final byte[] bytes = "byte[]".getBytes();
        this.out.write(bytes);
        assertMessages();
        this.out.write('\n');
        assertMessages("byte[]");
        if (this.wrapped != null) {
            assertEquals("byte[]\n", this.wrapped.toString());
        }
    }
