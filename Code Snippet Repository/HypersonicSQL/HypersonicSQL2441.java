    boolean setDataSpaceManager() {

        writeLock.lock();

        int fileSpaceSize = database.logger.propFileSpaceValue;

        try {
            if (fileSpaceSize > 0 && spaceManagerPosition == 0) {
                spaceManager.reset();

                spaceManager = new DataSpaceManagerBlocks(this);

                return true;
            }

            if (fileSpaceSize == 0 && spaceManagerPosition != 0) {
                spaceManager.reset();

                spaceManager = new DataSpaceManagerSimple(this, false);

                return true;
            }

            return false;
        } finally {
            writeLock.unlock();
        }
    }
