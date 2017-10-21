    public TableSpaceManager getTableSpace(int spaceId) {

        if (spaceId == DataSpaceManager.tableIdDefault) {
            return defaultSpaceManager;
        }

        if (spaceId >= spaceIdSequence.get()) {
            spaceIdSequence.set((spaceId + 2) & -2);
        }

        cache.writeLock.lock();

        try {
            TableSpaceManagerBlocks manager =
                (TableSpaceManagerBlocks) spaceManagerList.get(spaceId);

            if (manager == null) {
                int minReuse = cache.database.logger.propMinReuse;

                manager = new TableSpaceManagerBlocks(
                    this, spaceId, fileBlockSize,
                    cache.database.logger.propMaxFreeBlocks, dataFileScale,
                    minReuse);

                spaceManagerList.put(spaceId, manager);
            }

            return manager;
        } finally {
            cache.writeLock.unlock();
        }
    }
