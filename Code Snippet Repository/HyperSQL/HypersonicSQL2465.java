    private int getExistingBlockIndex(int tableId, int blockCount) {

        int blockIndex = emptySpaceList.removeFirstConsecutiveKeys(blockCount,
            -1);

        if (blockIndex > 0) {
            setDirectoryBlocksAsTable(tableId, blockIndex, blockCount);
        }

        return blockIndex;
    }
