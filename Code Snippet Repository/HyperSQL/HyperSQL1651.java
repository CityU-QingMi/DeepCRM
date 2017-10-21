    private long calculateDirectorySpaceSize(long blockCount) {

        long blockLimit = ArrayUtil.getBinaryMultipleCeiling(blockCount + 1,
            dirBlockSize);
        long currentSize = IntArrayCachedObject.fileSizeFactor * blockLimit;    // root

        currentSize += DirectoryBlockCachedObject.fileSizeFactor * blockLimit;    // directory
        currentSize += bitmapStorageSize * (blockCount + 1);                      // bitmaps

        return currentSize;
    }
