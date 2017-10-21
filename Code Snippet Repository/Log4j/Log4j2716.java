    @Test
    public void testWrite_ByteArray_Offset_Length() throws Exception {
        final byte[] bytes = "byte[]".getBytes();
        final int middle = bytes.length / 2;
        final int length = bytes.length - middle;
        final String right = new String(bytes, middle, length);
        this.out.write(bytes, middle, length);
        assertMessages();
        this.out.write('\n');
        assertMessages(right);
        if (this.wrapped != null) {
            assertEquals("byte[]".substring(middle, bytes.length) + '\n', this.wrapped.toString());
        }
    }
