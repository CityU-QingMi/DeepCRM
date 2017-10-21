    public synchronized InputStream getBinaryStream() throws SQLException {

        checkClosed();
        checkReadable();

        InputStream rval = getBinaryStreamImpl();

        setReadable(false);
        setWritable(false);

        return rval;
    }
