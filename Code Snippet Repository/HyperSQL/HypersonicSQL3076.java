    public void forceSync() {

        if (isClosed) {
            return;
        }

        needsSync = false;

        synchronized (fileStreamOut) {
            try {
                fileStreamOut.flush();
                outDescriptor.sync();

                syncCount++;
/**/
/**/
/**/
/**/
/**/
            } catch (IOException e) {
                database.logger.logWarningEvent("ScriptWriter synch error: ",
                                                e);
            }
        }
    }
