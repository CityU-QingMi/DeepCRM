    @Override
    public synchronized boolean closeOutputStream() {
        flush();
        try {
            randomAccessFile.close();
            return true;
        } catch (final IOException ex) {
            logError("Unable to close RandomAccessFile", ex);
            return false;
        }
    }
