    public void shutdownWithCatalogs(int shutdownMode) {

        // If an unchecked exception is thrown, isShuttingDown will be left true,
        // which is good from a security standpoint.
        isShuttingDown = true;

        // make handleConnection() reject new connection attempts
        DatabaseManager.shutdownDatabases(this, shutdownMode);
        shutdown(false);

        isShuttingDown = false;
    }
