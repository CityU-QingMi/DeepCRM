    private void ensureDirectorySpaceAvailable(int blockCount) {

        int dirObjectSize = bitmapStorageSize * blockCount;

        dirObjectSize += DirectoryBlockCachedObject.fileSizeFactor
                         * dirBlockSize;

        boolean hasRoom = directorySpaceManager.hasFileRoom(dirObjectSize);

        if (!hasRoom) {
            int index         = getBlockIndexLimit();
            int dirBlockCount = dirObjectSize / fileBlockSize + 1;
            long filePosition = cache.enlargeFileSpace((long) dirBlockCount
                * fileBlockSize);

            directorySpaceManager.addFileBlock(filePosition,
                                               filePosition
                                               + (long) dirBlockCount
                                                 * fileBlockSize);
            createFileBlocksInDirectory(index, dirBlockCount,
                                        tableIdDirectory);

            // integrity check
            index = getBlockIndexLimit();

            if ((long) index * fileBlockSize != cache.getFileFreePos()) {
                cache.logSevereEvent(
                    "space manager end file pos different from data file: "
                    + (index * fileBlockSize) + ", "
                    + cache.getFileFreePos(), null);
            }
        }
    }
