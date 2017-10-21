    @Test
    public void read() throws Exception {
        this.logIn.read();
        assertMessages("before read int size", 0, "read");
        this.logIn.read();
        assertMessages("after read int size", 1, "read");

        this.logIn.read(new byte[2]);
        assertMessages("after read bytes size", 2, "read");

        this.logIn.read(new byte[2], 0, 2);
        assertMessages("after read bytes offset size", 3, "read");

        this.logIn.read();
        assertMessages("before close size", 3, "read");
        this.logIn.close();
        assertMessages("after close size", 4, "read");
    }
