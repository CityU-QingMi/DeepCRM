    private void initialiseTableSpace(TableSpaceManagerBlocks tableSpace,
                                      int blockIndex) {

        // get existing file block and initialise
        ba.initialise(true);

        try {
            ba.moveToBlock(blockIndex);

            int  freeItems = ba.getFreeBlockValue();
            long blockPos  = (long) blockIndex * fileBlockSize;
            int unsetCount = ba.unsetRange(fileBlockItemCount - freeItems,
                                           freeItems);

            if (unsetCount == freeItems) {
                tableSpace.initialiseFileBlock(
                    null,
                    blockPos + (fileBlockSize - freeItems * dataFileScale),
                    blockPos + fileBlockSize);
            } else {
                cache.logSevereEvent("space manager error - recovered", null);
            }
        } finally {
            ba.reset();
        }
    }
