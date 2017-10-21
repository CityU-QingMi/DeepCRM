    public void addFileBlock(long blockFreePos, long blockLimit) {

        int released = (int) (freshBlockLimit - freshBlockFreePos);

        if (released > 0) {
            release(freshBlockFreePos / scale, released);
        }

        initialiseFileBlock(null, blockFreePos, blockLimit);
    }
