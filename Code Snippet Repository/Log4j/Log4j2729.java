    @Test
    public void testClose_NoRemainingData() throws IOException {
        this.wrapped = new ByteArrayInputStream((FIRST + '\n').getBytes());
        this.in = createInputStream();

        final byte[] bytes = new byte[1024];
        this.in.read(bytes);
        assertMessages(FIRST);
        this.in.close();
        assertMessages(FIRST);
    }
