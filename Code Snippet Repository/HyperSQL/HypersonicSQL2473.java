    private long calculateDirectorySpaceBlocks(long blockCount) {

        long currentSize   = calculateDirectorySpaceSize(blockCount);
        long currentBlocks = currentSize / fileBlockSize + 1;

        currentSize   += calculateDirectorySpaceSize(currentBlocks);
        currentBlocks = currentSize / fileBlockSize + 1;

        return currentBlocks;
    }
