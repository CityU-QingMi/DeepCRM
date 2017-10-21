    public synchronized OutputStream setBinaryStream() throws SQLException {

        checkClosed();
        checkWritable();

        OutputStream rval = setBinaryStreamImpl();

        setWritable(false);
        setReadable(true);

        return rval;
    }
