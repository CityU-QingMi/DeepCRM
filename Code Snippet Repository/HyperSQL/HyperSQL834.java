    public synchronized Writer setCharacterStream() throws SQLException {

        checkClosed();
        checkWritable();

        Writer writer = setCharacterStreamImpl();

        setReadable(true);
        setWritable(false);

        return writer;
    }
