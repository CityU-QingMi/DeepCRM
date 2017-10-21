    @Test
    public void read() throws Exception {
        this.logReader.read();
        assertMessages("before read int size", 0, "read");
        this.logReader.read();
        assertMessages("after read int size", 1, "read");

        this.logReader.read(new char[2]);
        assertMessages("after read bytes size", 2, "read");

        this.logReader.read(new char[2], 0, 2);
        assertMessages("after read bytes offset size", 3, "read");

        this.logReader.read(CharBuffer.allocate(2));
        assertMessages("after read charBuffer size", 4, "read");

        this.logReader.read();
        assertMessages("before close size", 4, "read");
        this.logReader.close();
        assertMessages("after close size", 5, "read");
    }
