    private final void closeRAF() throws LockFile.UnexpectedFileIOException {

        if (raf != null) {
            try {
                raf.close();
            } catch (IOException ex) {
                throw new UnexpectedFileIOException(this, "closeRAF", ex);
            } finally {
                raf = null;
            }
        }
    }
