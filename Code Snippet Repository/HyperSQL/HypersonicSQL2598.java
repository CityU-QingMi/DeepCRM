    private final void writeHeartbeat()
    throws LockFile.FileSecurityException,
           LockFile.UnexpectedEndOfFileException,
           LockFile.UnexpectedFileIOException {

        try {
            raf.seek(MAGIC.length);
            raf.writeLong(System.currentTimeMillis());
        } catch (SecurityException ex) {
            throw new FileSecurityException(this, "writeHeartbeat", ex);
        } catch (EOFException ex) {
            throw new UnexpectedEndOfFileException(this, "writeHeartbeat", ex);
        } catch (IOException ex) {
            throw new UnexpectedFileIOException(this, "writeHeartbeat", ex);
        }
    }
