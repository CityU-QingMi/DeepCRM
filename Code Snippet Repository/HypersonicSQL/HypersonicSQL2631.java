    public synchronized void setScriptType(int format) {

        if (format == propScriptFormat) {
            return;
        }

        propScriptFormat = format;

        checkpointState.compareAndSet(stateCheckpointNormal,
                                      stateCheckpointRequired);
    }
