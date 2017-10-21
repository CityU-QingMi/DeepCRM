    public synchronized CharArrayReader toCharArrayReader()
    throws IOException {

        checkFreed();

        CharArrayReader reader = new CharArrayReader(buf, 0, count);

        //System.out.println("toCharArrayReader::buf.length: " + buf.length);
        free();

        return reader;
    }
