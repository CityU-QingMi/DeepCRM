    public void setIsolation(int level) {

        if (isInMidTransaction()) {
            throw Error.error(ErrorCode.X_25001);
        }

        if (level == SessionInterface.TX_READ_UNCOMMITTED) {
            level = SessionInterface.TX_READ_COMMITTED;
        }

        if (isolationLevel != level) {
            isolationLevel = level;
            isReadOnlyIsolation = level
                                  == SessionInterface.TX_READ_UNCOMMITTED;
        }
    }
