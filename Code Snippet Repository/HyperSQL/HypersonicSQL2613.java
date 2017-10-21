    private final void writeMagic()
    throws LockFile.FileSecurityException,
           LockFile.UnexpectedEndOfFileException,
           LockFile.UnexpectedFileIOException {

        try {
            raf.seek(0);
            raf.write(MAGIC);
        } catch (SecurityException ex) {
            throw new FileSecurityException(this, "writeMagic", ex);
        } catch (EOFException ex) {
            throw new UnexpectedEndOfFileException(this, "writeMagic", ex);
        } catch (IOException ex) {
            throw new UnexpectedFileIOException(this, "writeMagic", ex);
        }
    }
