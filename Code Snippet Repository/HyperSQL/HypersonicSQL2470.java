    int findTableSpace(long position) {

        int blockIndex = (int) (position / fileBlockItemCount);

        cache.writeLock.lock();

        try {
            ba.initialise(false);

            try {
                boolean result = ba.moveToBlock(blockIndex);

                if (!result) {
                    return -1;
                }

                int id = ba.getTableId();

                return id;
            } finally {
                ba.reset();
            }
        } finally {
            cache.writeLock.unlock();
        }
    }
