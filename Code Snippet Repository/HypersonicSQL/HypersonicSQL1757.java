    public synchronized <T extends Result>T setResult(
            Class<T> resultClass) throws SQLException {

        checkClosed();
        checkWritable();

        final T result = createResult(resultClass);

        setReadable(true);
        setWritable(false);

        return result;
    }
