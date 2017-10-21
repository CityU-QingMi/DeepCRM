    @Test
    public void testRead_MultipleLines() throws IOException {
        this.wrapped = new ByteArrayInputStream((FIRST + "\n" + LAST + '\n').getBytes());
        this.in = createInputStream();

        final byte[] bytes = new byte[1024];
        final int len = this.in.read(bytes);
        this.read.write(bytes, 0, len);
        assertMessages(FIRST, LAST);
        assertEquals(FIRST + '\n' + LAST + '\n', this.read.toString());
    }
