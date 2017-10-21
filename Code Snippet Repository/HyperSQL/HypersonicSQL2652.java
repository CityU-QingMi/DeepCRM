    public void checkpoint(Session session, boolean defrag, boolean lobs) {

        if (!backupState.compareAndSet(stateNormal, stateCheckpoint)) {
            throw Error.error(ErrorCode.ACCESS_IS_DENIED);
        }

        database.lobManager.lock();

        try {
            synchronized (this) {
                checkpointInternal(session, defrag);

                if (lobs) {
                    database.lobManager.deleteUnusedLobs();
                }
            }
        } finally {
            backupState.set(stateNormal);
            checkpointState.set(stateCheckpointNormal);
            database.lobManager.unlock();
        }
    }
