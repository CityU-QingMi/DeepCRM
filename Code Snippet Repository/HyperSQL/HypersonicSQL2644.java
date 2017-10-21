    public void backup(String destPath, boolean script, boolean blocking,
                       boolean compressed, boolean files) {

        if (!backupState.compareAndSet(stateNormal, stateBackup)) {
            throw Error.error(ErrorCode.BACKUP_ERROR, "backup in progress");
        }

        if (blocking) {
            database.lobManager.lock();

            try {
                synchronized (this) {
                    backupInternal(destPath, script, blocking, compressed,
                                   files);
                }
            } finally {
                backupState.set(stateNormal);
                database.lobManager.unlock();
            }
        } else {
            try {
                backupInternal(destPath, script, blocking, compressed, files);
            } finally {
                backupState.set(stateNormal);
            }
        }
    }
