    void setIncrementBackup(boolean value) {

        writeLock.lock();

        try {
            int flags = getFlags();

            if (value) {
                flags = BitMap.set(flags, FLAG_ISSHADOWED);
            } else {
                flags = BitMap.unset(flags, FLAG_ISSHADOWED);
            }

            setFlags(flags);

            fileModified = true;
        } catch (Throwable t) {
            logSevereEvent("DataFileCache.setIncrementalBackup", t);
        } finally {
            writeLock.unlock();
        }
    }
