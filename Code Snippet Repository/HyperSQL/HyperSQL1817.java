    public synchronized void setIncrementBackup(boolean val) {

        if (val == propIncrementBackup) {
            return;
        }

        if (log != null) {
            log.setIncrementBackup(val);

            if (log.hasCache()) {
                checkpointState.compareAndSet(stateCheckpointNormal,
                                              stateCheckpointRequired);
            }
        }

        propIncrementBackup = val;
    }
