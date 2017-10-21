    public synchronized void setString(String value) throws SQLException {

        if (value == null) {
            throw JDBCUtil.nullArgument("value");
        }
        checkWritable();
        setStringImpl(value);
        setReadable(true);
        setWritable(false);
    }
