    @SuppressWarnings("")
    public synchronized <T extends Source>T getSource(
            Class<T> sourceClass) throws SQLException {

        checkClosed();
        checkReadable();

        final Source source = getSourceImpl(sourceClass);

        setReadable(false);
        setWritable(false);

        return (T) source;
    }
