    public synchronized Reader getCharacterStream() throws SQLException {

        checkClosed();
        checkReadable();

        Reader reader = getCharacterStreamImpl();

        setReadable(false);
        setWritable(false);

        return reader;
    }
