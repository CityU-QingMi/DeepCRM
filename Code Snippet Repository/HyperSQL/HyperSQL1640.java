    public void initialiseSpaces() {

        cache.writeLock.lock();

        try {
            Iterator it = spaceManagerList.values().iterator();

            while (it.hasNext()) {
                TableSpaceManagerBlocks tableSpace =
                    (TableSpaceManagerBlocks) it.next();

                if (tableSpace.getSpaceID() == DataSpaceManager
                        .tableIdDirectory || tableSpace
                        .getFileBlockIndex() != -1) {
                    initialiseTableSpace(tableSpace);
                }
            }
        } finally {
            cache.writeLock.unlock();
        }
    }
