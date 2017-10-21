    @Override
    public synchronized boolean closeOutputStream() {
        flush();
        try {
            randomAccessFile.close();
            return true;
        } catch (final IOException e) {
            logError("Unable to close RandomAccessFile", e);
            return false;
        }
    }
