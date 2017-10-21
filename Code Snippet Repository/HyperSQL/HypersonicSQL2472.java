    public long getLostBlocksSize() {

        long fragment = 0;

        cache.writeLock.lock();

        try {
            ba.initialise(false);

            try {
                for (;;) {
                    boolean result = ba.nextBlock();

                    if (!result) {
                        break;
                    }

                    if (ba.getTableId() == tableIdDirectory) {
                        continue;
                    }

                    fragment += ba.getFreeSpaceValue() * dataFileScale;

                    if (ba.getTableId() == tableIdEmpty) {
                        fragment += fileBlockSize;
                    }
                }
            } finally {
                ba.reset();
            }
        } finally {
            cache.writeLock.unlock();
        }

        return fragment;
    }
