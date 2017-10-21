    public synchronized String getString() throws SQLException {

        checkClosed();
        checkReadable();

        String value = getStringImpl();

        setReadable(false);
        setWritable(false);

        return value;
    }
