    long getNewBlock(long rowSize, boolean asBlocks) {

        if (asBlocks) {
            rowSize = (int) ArrayUtil.getBinaryMultipleCeiling(rowSize,
                    DataSpaceManager.fixedBlockSizeUnit);
        }

        if (freshBlockFreePos + rowSize > freshBlockLimit) {
            boolean result = getNewMainBlock(rowSize);

            if (!result) {
                throw Error.error(ErrorCode.DATA_FILE_IS_FULL);
            }
        }

        long position = freshBlockFreePos;

        if (asBlocks) {
            position = ArrayUtil.getBinaryMultipleCeiling(position,
                    DataSpaceManager.fixedBlockSizeUnit);

            long released = position - freshBlockFreePos;

            if (released > 0) {
                release(freshBlockFreePos / scale, (int) released);

                freshBlockFreePos = position;
            }
        }

        freshBlockFreePos += rowSize;

        return position / scale;
    }
