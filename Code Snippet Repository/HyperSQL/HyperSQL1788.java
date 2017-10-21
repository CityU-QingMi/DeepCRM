    public static LockFile newLockFileLock(final String path)
    throws HsqlException {

        LockFile lockFile = null;

        try {
            lockFile = LockFile.newLockFile(path + ".lck");
        } catch (LockFile.BaseException e) {
            throw Error.error(ErrorCode.LOCK_FILE_ACQUISITION_FAILURE,
                              e.getMessage());
        }

        boolean locked = false;

        try {
            locked = lockFile.tryLock();
        } catch (LockFile.BaseException e) {
            throw Error.error(ErrorCode.LOCK_FILE_ACQUISITION_FAILURE,
                              e.getMessage());
        }

        // Paranoia mode: In theory, this case can't happen, given the way
        // tryLock now works; by all current understanding of the involved API
        // contracts, an exception will always be thrown instead by the code
        // above.
        if (!locked) {
            throw Error.error(ErrorCode.LOCK_FILE_ACQUISITION_FAILURE,
                              lockFile.toString());
        }

        return lockFile;
    }
