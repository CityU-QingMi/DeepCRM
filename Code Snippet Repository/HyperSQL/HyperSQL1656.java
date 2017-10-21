    private long getNewFileBlocksNoCheck(int tableId, int blockCount) {

        long index        = getBlockIndexLimit();
        long filePosition = index * fileBlockSize;
        long delta = filePosition + ((long) blockCount * fileBlockSize)
                     - cache.getFileFreePos();

        if (delta > 0) {
            cache.enlargeFileSpace(delta);
        }

        createFileBlocksInDirectory((int) index, blockCount, tableId);

        return filePosition;
    }
