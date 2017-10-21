    public void reset() {

        if (freshBlockFreePos == 0) {
            fileBlockIndex = -1;
        } else {
            fileBlockIndex = (int) (freshBlockFreePos / mainBlockSize);
        }

        spaceManager.freeTableSpace(spaceID, lookup, freshBlockFreePos,
                                    freshBlockLimit, true);

        freshBlockFreePos = 0;
        freshBlockLimit   = 0;
    }
