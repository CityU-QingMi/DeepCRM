    private final void openRAF()
    throws LockFile.UnexpectedFileNotFoundException,
           LockFile.FileSecurityException, LockFile.UnexpectedFileIOException {

        try {
            raf = new RandomAccessFile(file, "rw");
        } catch (SecurityException ex) {
            throw new FileSecurityException(this, "openRAF", ex);
        } catch (FileNotFoundException ex) {
            throw new UnexpectedFileNotFoundException(this, "openRAF", ex);
        }
    }
