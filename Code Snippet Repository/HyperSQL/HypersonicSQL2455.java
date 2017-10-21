    public void close() {

        writeLock.lock();

        try {
            if (dataFile == null) {
                return;
            }

            reset();
            dataFile.close();
            logDetailEvent("dataFileCache file close end");

            dataFile = null;

            boolean empty = fileFreePosition == initialFreePos;

            if (empty) {
                deleteFile();
                deleteBackup();
            }
        } catch (HsqlException e) {
            throw e;
        } catch (Throwable t) {
            logSevereEvent("DataFileCache.close", t);

            throw Error.error(t, ErrorCode.FILE_IO_ERROR,
                              ErrorCode.M_DataFileCache_close, new Object[] {
                t.toString(), dataFileName
            });
        } finally {
            writeLock.unlock();
        }
    }
