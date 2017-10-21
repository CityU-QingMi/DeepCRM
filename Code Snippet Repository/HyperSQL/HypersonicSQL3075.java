    public void sync() {

        if (isClosed) {
            return;
        }

        if (needsSync) {
            forceSync();
        }
    }
