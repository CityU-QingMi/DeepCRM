    RowInputInterface getRaw(long pos) {

        writeLock.lock();

        try {
            readObject(pos);

            return rowIn;
        } finally {
            writeLock.unlock();
        }
    }
