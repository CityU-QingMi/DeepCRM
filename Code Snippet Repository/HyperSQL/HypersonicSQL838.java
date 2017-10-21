    public synchronized void setIsolationDefault(int level) {

        if (level == SessionInterface.TX_READ_UNCOMMITTED) {
            level = SessionInterface.TX_READ_COMMITTED;
        }

        if (level == isolationLevelDefault) {
            return;
        }

        isolationLevelDefault = level;

        if (!isInMidTransaction()) {
            isolationLevel = isolationLevelDefault;
            isReadOnlyIsolation = level
                                  == SessionInterface.TX_READ_UNCOMMITTED;
        }
    }
