    public void initialiseSpaces() {

        long currentSize = cache.getFileFreePos();
        long totalBlocks = (currentSize + fileBlockSize) / fileBlockSize;
        long lastFreePosition = cache.enlargeFileSpace(totalBlocks
            * fileBlockSize - currentSize);

        defaultSpaceManager.initialiseFileBlock(lookup, lastFreePosition,
                cache.getFileFreePos());

        if (lookup != null) {
            totalFragmentSize -= lookup.getTotalValues()
                                 * cache.getDataFileScale();
            lookup = null;
        }
    }
