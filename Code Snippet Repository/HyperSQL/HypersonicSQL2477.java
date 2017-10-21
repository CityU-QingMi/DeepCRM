    private void initialiseTableSpace(TableSpaceManagerBlocks tableSpace) {

        int spaceId        = tableSpace.getSpaceID();
        int blockIndex     = -1;
        int lastBlockIndex = tableSpace.getFileBlockIndex();

        if (lastBlockIndex >= 0) {
            if (hasFreeSpace(spaceId, lastBlockIndex)) {
                blockIndex = lastBlockIndex;
            }
        }

        if (blockIndex < 0) {
            blockIndex = findLargestFreeSpace(spaceId);
        }

        if (blockIndex < 0) {
            return;
        }

        initialiseTableSpace(tableSpace, blockIndex);
    }
