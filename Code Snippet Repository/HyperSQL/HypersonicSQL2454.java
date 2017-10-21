    public void release() {

        writeLock.lock();

        try {
            if (dataFile == null) {
                return;
            }

            if (shadowFile != null) {
                shadowFile.close();

                shadowFile = null;
            }

            dataFile.close();
            logDetailEvent("dataFileCache file closed");

            dataFile = null;
        } catch (Throwable t) {
            logSevereEvent("DataFileCache.release", t);
        } finally {
            writeLock.unlock();
        }
    }
