    void setAsideBlocks(OrderedIntHashSet blocks) {

        cache.writeLock.lock();

        try {
            ba.initialise(true);

            try {
                for (int i = 0; i < blocks.size(); i++) {
                    int     block  = blocks.get(i);
                    boolean result = ba.moveToBlock(block);

                    if (result) {
                        ba.setTable(DataSpaceManager.tableIdSetAside);
                    }
                }
            } finally {
                ba.reset();
            }
        } finally {
            cache.writeLock.unlock();
        }
    }
