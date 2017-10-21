    public void reopen() {

        writeLock.lock();

        try {
            openShadowFile();
            spaceManager.initialiseSpaces();
        } finally {
            writeLock.unlock();
        }
    }
