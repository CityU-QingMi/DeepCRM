    boolean getNewMainBlock(long rowSize) {

        long blockCount = (mainBlockSize + rowSize) / mainBlockSize;
        long blockSize  = blockCount * mainBlockSize;
        long position = spaceManager.getFileBlocks(spaceID, (int) blockCount);

        if (position < 0) {
            return false;
        }

        if (position != freshBlockLimit) {
            long released = freshBlockLimit - freshBlockFreePos;

            if (released > 0) {
                release(freshBlockFreePos / scale, (int) released);
            }

            freshBlockFreePos = position;
            freshBlockLimit   = position;
        }

        freshBlockLimit += blockSize;

        return true;
    }
