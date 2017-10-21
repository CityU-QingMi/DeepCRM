    @Test
    public void write() throws Exception {
        this.logOut.write('a');
        assertMessages("before write int", 0, "write");
        this.logOut.write('\n');
        assertMessages("after write int", 1, "write");
        
        this.logOut.write("b\n".getBytes());
        assertMessages("after write byte array", 2, "write");

        this.logOut.write("c\n".getBytes(), 0, 2);
        assertMessages("after write byte array offset size", 3, "write");

        this.logOut.write('d');
        assertMessages("before close size", 3, "write");
        this.logOut.close();
        assertMessages("after close size", 4, "write");
    }
