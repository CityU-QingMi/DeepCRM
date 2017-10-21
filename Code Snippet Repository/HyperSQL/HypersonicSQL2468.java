    public void freeTableSpace(int spaceId) {

        if (spaceId == tableIdDefault || spaceId == tableIdDirectory) {
            return;
        }

        cache.writeLock.lock();

        try {
            TableSpaceManager tableSpace =
                (TableSpaceManager) spaceManagerList.get(spaceId);

            if (tableSpace != null) {
                tableSpace.reset();
                spaceManagerList.remove(spaceId);
            }

            IntIndex list = new IntIndex(16, false);

            ba.initialise(true);

            try {
                while (ba.nextBlockForTable(spaceId)) {
                    list.addUnsorted(ba.currentBlockIndex);
                    ba.setTable(tableIdEmpty);
                    emptySpaceList.addUnique(ba.currentBlockIndex);
                }
            } finally {
                ba.reset();
            }

            cache.releaseRange(list, fileBlockItemCount);
        } finally {
            cache.writeLock.unlock();
        }
    }
