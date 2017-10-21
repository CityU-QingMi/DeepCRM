    private void initialiseNewSpaceDirectory() {

        long currentSize = cache.getFileFreePos();
        long totalBlocks = (currentSize / fileBlockSize) + 1;
        long lastFreePosition = cache.enlargeFileSpace(totalBlocks
            * fileBlockSize - currentSize);

        defaultSpaceManager.initialiseFileBlock(null, lastFreePosition,
                cache.getFileFreePos());

        long defaultSpaceBlockCount = totalBlocks;
        long directorySpaceBlockCount =
            calculateDirectorySpaceBlocks(totalBlocks);

        lastFreePosition = cache.enlargeFileSpace(directorySpaceBlockCount
                * fileBlockSize);

        // file block is empty
        directorySpaceManager.initialiseFileBlock(null, lastFreePosition,
                cache.getFileFreePos());

        IntArrayCachedObject root = new IntArrayCachedObject(dirBlockSize);

        rootStore.add(root, true);

        rootBlock = root;

        createFileBlocksInDirectory((int) defaultSpaceBlockCount,
                                    (int) directorySpaceBlockCount,
                                    tableIdDirectory);
        createFileBlocksInDirectory(0, (int) defaultSpaceBlockCount,
                                    tableIdDefault);
    }
