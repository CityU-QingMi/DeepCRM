    private int findLargestFreeSpace(int spaceId) {

        int maxFree    = 0;
        int blockIndex = -1;

        ba.initialise(false);

        try {
            for (; ba.nextBlockForTable(spaceId); ) {

                // find the largest free
                int currentFree = ba.getFreeBlockValue();

                if (currentFree > maxFree) {
                    blockIndex = ba.currentBlockIndex;
                    maxFree    = currentFree;
                }
            }

            return blockIndex;
        } finally {
            ba.reset();
        }
    }
