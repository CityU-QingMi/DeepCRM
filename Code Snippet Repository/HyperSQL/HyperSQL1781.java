    public static boolean isLocked(final String path) {

        boolean locked = true;

        try {
            LockFile lockFile = LockFile.newLockFile(path);

            lockFile.checkHeartbeat(false);

            locked = false;
        } catch (Exception e) {}

        return locked;
    }
