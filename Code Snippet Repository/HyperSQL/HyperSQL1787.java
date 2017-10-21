    public static LockFile newLockFile(final String path)
    throws FileCanonicalizationException, FileSecurityException {

        LockFile lockFile = newNIOLockFile();

        if (lockFile == null) {
            lockFile = new LockFile();
        }

        lockFile.setPath(path);

        return lockFile;
    }
