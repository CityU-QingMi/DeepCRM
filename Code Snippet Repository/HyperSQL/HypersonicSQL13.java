    public synchronized void setAutoCommit(boolean mode) {

        if (mode != isAutoCommit) {
            setAttribute(SessionInterface.INFO_AUTOCOMMIT, mode ? Boolean.TRUE
                                                                : Boolean
                                                                .FALSE);

            isAutoCommit = mode;
        }
    }
