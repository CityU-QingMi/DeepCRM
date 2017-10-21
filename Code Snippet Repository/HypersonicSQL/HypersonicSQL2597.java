    private static LockFile newNIOLockFile() {

        if (NIO_FILELOCK_AVAILABLE && NIO_LOCKFILE_CLASS != null) {
            try {
                return (LockFile) NIO_LOCKFILE_CLASS.newInstance();
            } catch (Exception e) {

                // e.printStackTrace()
            }
        }

        return null;
    }
