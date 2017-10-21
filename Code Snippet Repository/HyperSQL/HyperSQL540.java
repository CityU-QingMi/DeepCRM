    public synchronized void close() throws SQLException {

        if (isClosed()) {
            return;
        }

        // outRegistrationMap = null;
        parameterNameMap = null;

        super.close();
    }
